package com.ccoins.prizes.service.impl;

import com.ccoins.prizes.dto.*;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ccoins.prizes.utils.enums.GiveLeaderEnum.*;

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
    public void addClientToParty(ClientPartyDTO request) {

        try{
            this.clientPartyRepository.save(ClientParty.builder()
                    .client(request.getClient())
                    .party(request.getParty())
                    .active(request.isActive())
                    .leader(request.isLeader())
                    .build());
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
    public List<ClientPartyDTO> findClientsByPartyId(Long id) {

        List<ClientPartyDTO> list = new ArrayList<>();
        List<ClientParty> clientPartyList;

        try {
            clientPartyList = this.clientPartyRepository.findByParty(id);
        }catch(Exception e){
            clientPartyList = new ArrayList<>();
        }

        for (ClientParty cp: clientPartyList) {
            list.add(MapperUtils.map(cp,ClientPartyDTO.class));
        }
        return list;
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

    @Override
    public List<Long> findAllIdsByClients(LongListDTO list) {

        try{
            Optional<List<Long>> partyOpt = this.repository.findAllPartyIdIn(list.getList());
            return partyOpt.orElseGet(ArrayList::new);
        }catch (Exception e){
            throw new ObjectNotFoundException(ExceptionConstant.PARTY_ID_BY_CLIENTS_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PARTY_ID_BY_CLIENTS_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<GenericRsDTO<ResponseDTO>> giveLeaderTo(Long leaderId, Long newLeaderId) {

        Optional<ClientParty> leaderOpt = this.clientPartyRepository.findById(leaderId);

        if(leaderOpt.isEmpty()){
            return ResponseEntity.ok(new GenericRsDTO<>(LEADER_NOT_FOUND.getCode(), LEADER_NOT_FOUND.getMessage(), null));
        }

        ClientParty leader = leaderOpt.get();

        if(!leader.isLeader() && !leader.isActive()){
            return ResponseEntity.ok(new GenericRsDTO<>(YOU_ARE_NOT_LEADER_FOUND.getCode(), YOU_ARE_NOT_LEADER_FOUND.getMessage(), null));
        }

        Optional<ClientParty> newLeaderOpt = this.clientPartyRepository.findById(newLeaderId);

        if(newLeaderOpt.isEmpty()){
            return ResponseEntity.ok(new GenericRsDTO<>(NEW_LEADER_NOT_FOUND.getCode(), NEW_LEADER_NOT_FOUND.getMessage(), null));
        }

        ClientParty newLeader = newLeaderOpt.get();

        leader.setLeader(false);
        newLeader.setLeader(true);

        List<ClientParty> clients = new ArrayList<>();
        clients.add(leader);
        clients.add(newLeader);

        this.clientPartyRepository.saveAll(clients);

        Optional<String> stringOpt = this.clientPartyRepository.findClientNameById(newLeaderId);

        if(stringOpt.isEmpty()){
            return ResponseEntity.ok(new GenericRsDTO<>(ALL_HAIL_NEW_LEADER.getCode(), ALL_HAIL_NEW_LEADER.getMessage(), null));
        }

        return ResponseEntity.ok(new GenericRsDTO<>(ALL_HAIL_NEW_LEADER.getCode(), ALL_HAIL_NEW_LEADER_NAME.getMessage().replace("?", stringOpt.get()), null));
    }

    @Override
    public boolean closePartyIfHaveNoClients(Long partyId) {
        Long response = this.repository.closePartyIfHaveNoClients(partyId);
        return response > 0;
    }

    @Override
    public ResponseEntity<Boolean> isLeaderFromParty(String leaderIp, Long partyId) {
        return ResponseEntity.ok(this.repository.isLeaderFromParty(leaderIp, partyId));
    }

    @Override
    public void banClientFromParty(Long clientId, Long partyId) {
        this.repository.banClientFromParty(clientId, partyId);
    }

    @Override
    public ResponseEntity<Boolean> isBannedFromParty(ClientTableDTO request) {

        boolean response = this.clientPartyRepository.isBannedFromParty(request.getClientIp(), request.getTableCode());

        return ResponseEntity.ok(response);
    }
}
