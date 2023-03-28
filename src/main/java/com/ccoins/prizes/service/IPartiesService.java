package com.ccoins.prizes.service;

import com.ccoins.prizes.dto.LongListDTO;
import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.model.projection.IPParty;

import java.util.List;
import java.util.Optional;

public interface IPartiesService {

    Optional<IPParty> findActivePartyByTable(Long id);

    PartyDTO createByTable(PartyDTO partyDTO);

    void addClientToParty(Long partyId, Long clientId);

    Optional<PartyDTO> findById(Long id);

    List<Long> findClientsByPartyId(Long id);

    void logoutClientFromTables(String client);

    Optional<PartyDTO> findActivePartyByTableCode(String code);

    List<Long> findAllIdsByClients(LongListDTO list);
}
