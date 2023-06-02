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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        try {
            return this.repository.findByTableAndActive(id, true);
        }catch(Exception e){
            throw new BadRequestException(ExceptionConstant.PARTY_FIND_ACTIVE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PARTY_FIND_ACTIVE_ERROR);
        }
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

            response = MapperUtils.map(party, PartyDTO.class);
        }catch (Exception e) {
            throw new BadRequestException(ExceptionConstant.PARTY_CREATE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PARTY_CREATE_ERROR);
        }

        return response;

    }

    @Override
    public ResponseEntity<ClientPartyDTO> addClientToParty(ClientPartyDTO request) {

        try{
            Optional<ClientParty> clientParty = this.clientPartyRepository.findByPartyAndClientAndActiveIsTrue(request.getParty(), request.getClient());
            ClientParty cp;
            if(clientParty.isEmpty()) {
                cp = this.clientPartyRepository.save(ClientParty.builder()
                        .client(request.getClient())
                        .party(request.getParty())
                        .active(request.isActive())
                        .leader(request.isLeader())
                        .build());
            }else{
                cp = clientParty.get();
            }

            return ResponseEntity.ok(MapperUtils.map(cp, ClientPartyDTO.class));

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
                response = MapperUtils.map(partyOpt.get(),PartyDTO.class);
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

        try {

            List<ClientParty> clientParties = this.clientPartyRepository.findByIpAndActiveTrue(client);

            clientParties.forEach(clientParty -> {
                clientParty.setActive(false);
                clientParty = this.clientPartyRepository.save(clientParty);
                if(clientParty.isLeader()) {
                    this.giveLeaderWhenLogout(client, clientParty);
                }
            });
        }catch(Exception e){
            throw new ObjectNotFoundException(ExceptionConstant.LOGOUT_CLIENTS_ERROR_CODE,
                    this.getClass(), ExceptionConstant.LOGOUT_CLIENTS_ERROR);
        }
    }

    public void giveLeaderWhenLogout(String client, ClientParty  clientParty){

        List<ClientParty> cp = this.clientPartyRepository.findByParty(clientParty.getParty());

        if(cp.isEmpty()) {
            return;
        }
        ClientParty newLeader = cp.stream().filter(ClientParty::isActive).collect(Collectors.toList()).stream().findFirst().orElse(null);

        if(newLeader == null) {
            return;
        }

        this.giveLeaderTo(client, newLeader.getClient());
    }

    @Override
    public Optional<PartyDTO> findActivePartyByTableCode(String code) {

        Optional<Party> partyOpt = Optional.empty();
        PartyDTO party = null;

        try {
            partyOpt = this.repository.findByTableCode(code);
        }catch (Exception e){
            throw new ObjectNotFoundException(ExceptionConstant.ACTIVE_PARTY_BY_TABLE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.ACTIVE_PARTY_BY_TABLE_ERROR);
        }
        if(partyOpt.isPresent())
            party = MapperUtils.map(partyOpt.get(), PartyDTO.class);

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
    public ResponseEntity<GenericRsDTO<ResponseDTO>> giveLeaderTo(String leaderIp, Long newLeaderId) {

        Optional<ClientParty> leaderOpt = Optional.empty();
        Optional<ClientParty> newLeaderOpt = Optional.empty();
        Optional<String> stringOpt;

        try {
            leaderOpt = this.clientPartyRepository.findByIp(leaderIp);
        }catch (Exception ignored){}

        if(leaderOpt.isEmpty()){
            return ResponseEntity.ok(new GenericRsDTO<>(LEADER_NOT_FOUND.getCode(), LEADER_NOT_FOUND.getMessage(), null));
        }

        ClientParty leader = leaderOpt.get();

        if(!leader.isLeader() && !leader.isActive()){
            return ResponseEntity.ok(new GenericRsDTO<>(YOU_ARE_NOT_LEADER_FOUND.getCode(), YOU_ARE_NOT_LEADER_FOUND.getMessage(), null));
        }

        try {
            newLeaderOpt = this.clientPartyRepository.findById(newLeaderId);
        }catch (Exception ignored){}

        if(newLeaderOpt.isEmpty()){
            return ResponseEntity.ok(new GenericRsDTO<>(NEW_LEADER_NOT_FOUND.getCode(), NEW_LEADER_NOT_FOUND.getMessage(), null));
        }

        ClientParty newLeader = newLeaderOpt.get();

        leader.setLeader(false);
        newLeader.setLeader(true);

        List<ClientParty> clients = new ArrayList<>();
        clients.add(leader);
        clients.add(newLeader);

        try {
            this.clientPartyRepository.saveAll(clients);
            stringOpt = this.clientPartyRepository.findClientNameById(newLeaderId);
        }catch(Exception e){
            throw new ObjectNotFoundException(ExceptionConstant.SAVE_ALL_CLIENTS_ERROR_CODE,
                    this.getClass(), ExceptionConstant.SAVE_ALL_CLIENTS_ERROR);
        }
        if(stringOpt.isEmpty()){
            return ResponseEntity.ok(new GenericRsDTO<>(ALL_HAIL_NEW_LEADER.getCode(), ALL_HAIL_NEW_LEADER.getMessage(), null));
        }

        return ResponseEntity.ok(new GenericRsDTO<>(ALL_HAIL_NEW_LEADER.getCode(), ALL_HAIL_NEW_LEADER_NAME.getMessage().concat(stringOpt.get()), null));
    }

    @Override
    public boolean closePartyIfHaveNoClients(Long partyId) {

        try {
            Integer response = this.repository.closePartyIfHaveNoClients(partyId);
            return response > 0L;
        }catch(Exception e){
            throw new BadRequestException(ExceptionConstant.CLOSING_PARTY_ERROR_CODE,
                    this.getClass(), ExceptionConstant.CLOSING_PARTY_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> isLeaderFromParty(String leaderIp, Long partyId) {

        Integer isLeader = null;

        try {
            isLeader = this.repository.isLeaderFromParty(leaderIp, partyId);
        }catch (Exception e){
            ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(isLeader != null && isLeader == 1);
    }

    @Override
    public void banClientFromParty(Long clientId, Long partyId) {
        try {
            this.repository.banClientFromParty(clientId, partyId);
        }catch(Exception e){
            throw new BadRequestException(ExceptionConstant.BAN_CLIENT_ERROR_CODE,
                    this.getClass(), ExceptionConstant.BAN_CLIENT_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> isBannedFromParty(ClientTableDTO request) {

        try {
            Boolean response = this.clientPartyRepository.isBannedFromParty(request.getClientIp(), request.getTableCode());

            if(response ==  null){
                response = false;
            }
            return ResponseEntity.ok(response);
        }catch(Exception e){
            throw new ObjectNotFoundException(ExceptionConstant.IS_BANNED_CLIENT_ERROR_CODE,
                    this.getClass(), ExceptionConstant.IS_BANNED_CLIENT_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<PartyDTO>> findActivePartiesByBar(Long id) {

        List<PartyDTO> response = new ArrayList<>();
        Optional<List<Party>> partiesOpt = Optional.empty();
        try {
            partiesOpt = this.repository.findActivePartiesByBar(id);
        }catch (Exception e){
            log.error("Falla al conectar con la base.");
        }

        if (partiesOpt.isPresent()) {
            List<Party> parties = partiesOpt.get();
            parties.forEach(party -> response.add(MapperUtils.map(party, PartyDTO.class)));
        }
        return ResponseEntity.ok(response);

    }
}
