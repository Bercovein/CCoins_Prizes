package com.ccoins.prizes.service;

import com.ccoins.prizes.dto.*;
import com.ccoins.prizes.model.projection.IPParty;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IPartiesService {

    Optional<IPParty> findActivePartyByTable(Long id);

    PartyDTO createByTable(PartyDTO partyDTO);

    ResponseEntity<ClientPartyDTO> addClientToParty(ClientPartyDTO request);

    Optional<PartyDTO> findById(Long id);

    List<ClientPartyDTO> findClientsByPartyId(Long id);

    void logoutClientFromParties(String client);

    void logoutClientFromPartiesBut(String client, Long partyId);

    Optional<PartyDTO> findActivePartyByTableCode(String code);

    List<Long> findAllIdsByClients(LongListDTO list);

    ResponseEntity<GenericRsDTO<ResponseDTO>> giveLeaderTo(String leaderId, Long clientId);

    boolean closePartyIfHaveNoClients(Long partyId);

    ResponseEntity<Boolean> isLeaderFromParty(String leaderIp, Long partyId);

    void banClientFromParty(Long clientId, Long partyId);

    ResponseEntity<Boolean> isBannedFromParty(ClientTableDTO request);

    ResponseEntity<List<PartyDTO>> findActivePartiesByBar(Long id);
}
