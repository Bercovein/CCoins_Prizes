package com.ccoins.prizes.service.impl;

import com.ccoins.prizes.dto.GenericRsDTO;
import com.ccoins.prizes.dto.ListDTO;
import com.ccoins.prizes.dto.PrizeDTO;
import com.ccoins.prizes.exceptions.UnauthorizedException;
import com.ccoins.prizes.exceptions.constant.ExceptionConstant;
import com.ccoins.prizes.model.Prize;
import com.ccoins.prizes.model.projection.IPPrize;
import com.ccoins.prizes.repository.IPrizeRepository;
import com.ccoins.prizes.service.IPrizeService;
import com.ccoins.prizes.utils.MapperUtils;
import com.ccoins.prizes.utils.StateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ccoins.prizes.utils.ResponseMessages.PRIZES_UPDATES_BY_QUANTITY;
import static com.ccoins.prizes.utils.ResponseMessages.SUCCESS_CODE;

@Service
@Slf4j
public class PrizeService implements IPrizeService {

    private final IPrizeRepository repository;

    @Autowired
    public PrizeService(IPrizeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<PrizeDTO> saveOrUpdate(PrizeDTO request) {

        try {
            Prize prize = this.repository.save(MapperUtils.map(request,Prize.class));
            return ResponseEntity.ok( MapperUtils.map(prize,PrizeDTO.class));
        }catch(Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_CREATE_OR_UPDATE_ERROR_CODE, this.getClass(), ExceptionConstant.PRIZE_CREATE_OR_UPDATE_ERROR);
        }
    }

    @Override
    public ResponseEntity<ListDTO> findAllByBarAndOptStatus(Long id, Optional<String> statusOpt) {

        ListDTO response = new ListDTO(new ArrayList<>());
        Optional<List<IPPrize>> listOpt;
        try {

            if(statusOpt.isPresent()){
                String status = statusOpt.get();
                boolean state = StateUtils.isActive(status);

                listOpt = this.repository.findByBarAndActive(id, state);
            }else{
                listOpt = this.repository.findByBar(id);
            }

            listOpt.ifPresent(response::setList);

            return ResponseEntity.ok(response);
        }catch(Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_FIND_BY_BAR_ERROR_CODE,
                    this.getClass(),
                    ExceptionConstant.PRIZE_FIND_BY_BAR_ERROR);
        }
    }

    @Override
    public ResponseEntity<PrizeDTO> findById(Long id) {

        try {
            Optional<Prize> bar = this.repository.findById(id);
            return ResponseEntity.ok(MapperUtils.map(bar,PrizeDTO.class));
        }catch(Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_FIND_BY_ID_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PRIZE_FIND_BY_ID_ERROR);
        }
    }

    @Override
    public ResponseEntity<PrizeDTO> active(Long id) {

        try {
            this.repository.updateActive(id);
        }catch(Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_UPDATE_ACTIVE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PRIZE_UPDATE_ACTIVE_ERROR);
        }
        return this.findById(id);
    }

    @Override
    public ResponseEntity<GenericRsDTO> activeByList(ListDTO request){

        try {
            List<Long> requestList = (List<Long>) request.getList();

            this.repository.updateActiveList(requestList);

            return this.findByIdIn(requestList);
        }catch (Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_UPDATE_LIST_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PRIZE_UPDATE_LIST_ERROR);
        }

    }

    @Override
    public ResponseEntity<GenericRsDTO> findByIdIn(List<Long> request){

        Optional<List<Prize>> listOpt;

        try {
            listOpt = this.repository.findByIdIn(request);
        }catch (Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_FIND_LIST_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PRIZE_FIND_LIST_ERROR);
        }

        List<PrizeDTO> list = new ArrayList<>();

        if(listOpt.isPresent()){
            List<Prize> prizes = listOpt.get();
            prizes.forEach(t -> list.add(MapperUtils.map(t,PrizeDTO.class)));
        }

        return ResponseEntity.ok(new GenericRsDTO<>(SUCCESS_CODE,String.format(PRIZES_UPDATES_BY_QUANTITY,list.size()),list));
    }

}
