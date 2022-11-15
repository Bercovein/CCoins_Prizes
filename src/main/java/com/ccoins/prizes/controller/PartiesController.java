package com.ccoins.prizes.controller;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.service.IPartiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/table")
    PartyDTO createParty(@RequestBody PartyDTO partyDTO){
        return this.service.createByTable(partyDTO);
    }

    @PostMapping({"/{partyId}/client/{clientId}"})
    @ResponseStatus(HttpStatus.OK)
    void addClientToParty(@PathVariable("partyId") Long partyId, @PathVariable("clientId") Long clientId) {
        this.service.addClientToParty(partyId,clientId);
    }

    @GetMapping("/{id}")
    Optional<PartyDTO> findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @GetMapping("/{id}/clients")
    List<Long> findClientsByPartyId(@PathVariable("id") Long id){
        return this.service.findClientsByPartyId(id);
    }

    @DeleteMapping("/client/{client}")
    void logoutClientFromTables(String client){
        this.service.logoutClientFromTables(client);
    }

    @GetMapping("/table/code/{code}")
    Optional<PartyDTO> findActivePartyByTableCode(@PathVariable ("code")String code){
       return this.service.findActivePartyByTableCode(code);
    }

}
