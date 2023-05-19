package com.ccoins.prizes.service;

import com.ccoins.prizes.dto.*;
import com.ccoins.prizes.model.projection.IPParty;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IPartiesService {

    Optional<IPParty> findActivePartyByTable(Long id);

    PartyDTO createByTable(PartyDTO partyDTO);

    void addClientToParty(ClientPartyDTO request);

    Optional<PartyDTO> findById(Long id);

    List<ClientPartyDTO> findClientsByPartyId(Long id);

    void logoutClientFromTables(String client);

    Optional<PartyDTO> findActivePartyByTableCode(String code);

    List<Long> findAllIdsByClients(LongListDTO list);

    ResponseEntity<GenericRsDTO<ResponseDTO>> giveLeaderTo(Long leaderId, Long clientId);

    boolean closePartyIfHaveNoClients(Long partyId);

    ResponseEntity<Boolean> isLeaderFromParty(String leaderIp, Long partyId);

    void banClientFromParty(Long clientId, Long partyId);

    ResponseEntity<Boolean> isBannedFromParty(ClientTableDTO request);
}
