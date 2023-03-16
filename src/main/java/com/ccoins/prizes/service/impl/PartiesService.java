package com.ccoins.prizes.service.impl;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.exceptions.BadRequestException;
import com.ccoins.prizes.exceptions.ObjectNotFoundException;
import com.ccoins.prizes.exceptions.constant.ExceptionConstant;
import com.ccoins.prizes.model.ClientParty;
import com.ccoins.prizes.model.Party;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.repository.IClientPartyRepository;
import com.ccoins.prizes.repository.IPartyRepository;
import com.ccoins.prizes.service.IPartiesService;
import com.ccoins.prizes.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PartiesService implements IPartiesService {

    private final IPartyRepository repository;

    private final IClientPartyRepository clientPartyRepository;

    @Autowired
    public PartiesService(IPartyRepository repository, IClientPartyRepository clientPartyRepository) {
        this.repository = repository;
        this.clientPartyRepository = clientPartyRepository;
    }

    @Override
    public Optional<IPParty> findActivePartyByTable(Long id) {
        return this.repository.findByTableAndActive(id,true);
    }

    @Override
    public PartyDTO createByTable(PartyDTO partyDTO) {

        PartyDTO response;

        try {
            Party party = Party.builder()
                    .active(true)
                    .table(partyDTO.getTable())
                    .name(partyDTO.getName())
                    .startDate(LocalDateTime.now())
                    .build();

            party = this.repository.save(party);

            response = (PartyDTO) MapperUtils.map(party, PartyDTO.class);
        }catch (Exception e) {
            throw new BadRequestException(ExceptionConstant.PARTY_CREATE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PARTY_CREATE_ERROR);
        }

        return response;

    }

    @Override
    public void addClientToParty(Long partyId, Long clientId) {

        try{
            this.clientPartyRepository.save(ClientParty.builder().client(clientId).party(partyId).active(true).build());
        }catch (Exception e) {
            throw new BadRequestException(ExceptionConstant.CLIENT_PARTY_SAVE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.CLIENT_PARTY_SAVE_ERROR);
        }
    }

    @Override
    public Optional<PartyDTO> findById(Long id){

        PartyDTO response = null;

        try{
            Optional<Party> partyOpt = this.repository.findById(id);

            if(partyOpt.isPresent()){
                response = (PartyDTO) MapperUtils.map(partyOpt.get(),PartyDTO.class);
            }
            return Optional.ofNullable(response);
        }catch (Exception e){
            throw new ObjectNotFoundException(ExceptionConstant.PARTY_FIND_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PARTY_FIND_ERROR);
        }
    }

    @Override
    public List<Long> findClientsByPartyId(Long id) {

        List<Long> idList = new ArrayList<>();
        List<ClientParty> clientPartyList;

        try {
            clientPartyList = this.clientPartyRepository.findByParty(id);
        }catch(Exception e){
            clientPartyList = new ArrayList<>();
        }

        for (ClientParty cp: clientPartyList) {
            idList.add(cp.getClient());
        }
        return idList;
    }

    @Override
    public void logoutClientFromTables(String client) {
        List<ClientParty> tables = this.clientPartyRepository.findByClientAndActive(client, true);

        tables.forEach(table -> {table.setActive(false);
            this.clientPartyRepository.save(table);
        });
    }

    @Override
    public Optional<PartyDTO> findActivePartyByTableCode(String code) {

        Optional<Party> partyOpt = this.repository.findByTableCode(code);
        PartyDTO party = null;

        if(partyOpt.isPresent())
            party = (PartyDTO) MapperUtils.map(partyOpt.get(), PartyDTO.class);

        return Optional.ofNullable(party);
    }
}
