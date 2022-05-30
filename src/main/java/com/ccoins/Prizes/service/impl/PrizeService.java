package com.ccoins.Prizes.service.impl;

import com.ccoins.Prizes.dto.ListDTO;
import com.ccoins.Prizes.dto.PrizeDTO;
import com.ccoins.Prizes.exceptions.UnauthorizedException;
import com.ccoins.Prizes.exceptions.constant.ExceptionConstant;
import com.ccoins.Prizes.model.Prize;
import com.ccoins.Prizes.repository.IPrizeRepository;
import com.ccoins.Prizes.service.IPrizeService;
import com.ccoins.Prizes.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Prize prize = this.repository.save((Prize)MapperUtils.map(request,Prize.class));
            return ResponseEntity.ok((PrizeDTO) MapperUtils.map(prize,PrizeDTO.class));
        }catch(Exception e){
            throw new UnauthorizedException(ExceptionConstant.PRIZE_CREATE_OR_UPDATE_ERROR_CODE, this.getClass(), ExceptionConstant.PRIZE_CREATE_OR_UPDATE_ERROR);
        }
    }

    @Override
    public ResponseEntity<ListDTO> findAllByBar(Long id) {

        ListDTO response = new ListDTO(new ArrayList<>());

        try {
            Optional<List<Prize>> barsOpt = this.repository.findByBar(id);

            if(barsOpt.isPresent()){
                List<Prize> bars = barsOpt.get();
                response.setList(MapperUtils.mapList(bars, PrizeDTO.class));
            }

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
            return ResponseEntity.ok((PrizeDTO)MapperUtils.map(bar,PrizeDTO.class));
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
}
