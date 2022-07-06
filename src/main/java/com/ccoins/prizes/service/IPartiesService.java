package com.ccoins.prizes.service;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.model.projection.IPParty;

import java.util.Optional;

public interface IPartiesService {

    Optional<IPParty> findActivePartyByTable(Long id);

    PartyDTO createByTable(Long id);

    void addClientToParty(Long partyId, Long clientId);
}
