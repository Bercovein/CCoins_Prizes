package com.ccoins.prizes.controller;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.service.IPartiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/parties")
public class PartiesController {

    private final IPartiesService service;

    @Autowired
    public PartiesController(IPartiesService service) {
        this.service = service;
    }

    @GetMapping("/table/{id}")
    Optional<IPParty> findActivePartyByTable(@PathVariable("id") Long id){
        return this.service.findActivePartyByTable(id);
    }

    @PostMapping("/table/{id}")
    PartyDTO createParty(@PathVariable("id")Long id){
        return this.service.createByTable(id);
    }

    @PostMapping({"/{partyId}/client/{clientId}"})
    @ResponseStatus(HttpStatus.OK)
    void addClientToParty(@PathVariable("partyId") Long partyId, @PathVariable("clientId") Long clientId) {
        this.service.addClientToParty(partyId,clientId);
    }
}
