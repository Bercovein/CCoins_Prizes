package com.ccoins.prizes.controller;

import com.ccoins.prizes.dto.*;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.service.IPartiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/bar/{id}")
    ResponseEntity<List<PartyDTO>> findActivePartiesByBar(@PathVariable("id") Long id){
        return this.service.findActivePartiesByBar(id);
    }

    @PostMapping("/table")
    PartyDTO createParty(@RequestBody PartyDTO partyDTO){
        return this.service.createByTable(partyDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping({"/client"})
    ResponseEntity<ClientPartyDTO> asignClientToParty(@RequestBody ClientPartyDTO request) {
        return this.service.addClientToParty(request);
    }

    @GetMapping("/{id}")
    Optional<PartyDTO> findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @GetMapping("/{id}/clients")
    List<ClientPartyDTO> findClientsByPartyId(@PathVariable("id") Long id){
        return this.service.findClientsByPartyId(id);
    }

    @DeleteMapping("/client/{client}")
    void logoutClientFromParties(@PathVariable("client") String client){
        this.service.logoutClientFromParties(client);
    }

    @GetMapping("/table/code/{code}")
    Optional<PartyDTO> findActivePartyByTableCode(@PathVariable("code") String code){
       return this.service.findActivePartyByTableCode(code);
    }

    @PostMapping("/clients")
    List<Long> findAllIdsByClients(@RequestBody LongListDTO list){
        return this.service.findAllIdsByClients(list);
    }

    @PutMapping(value = "/leader/{leaderId}/to/{clientId}")
    ResponseEntity<GenericRsDTO<ResponseDTO>> giveLeaderTo(@PathVariable("leaderId") String leaderId, @PathVariable("clientId") Long clientId){
        return this.service.giveLeaderTo(leaderId, clientId);
    }

    @GetMapping("/leader/{leaderIp}/party/{partyId}")
    ResponseEntity<Boolean> isLeaderFromParty(@PathVariable("leaderIp") String leaderIp, @PathVariable("partyId") Long partyId){
        return this.service.isLeaderFromParty(leaderIp, partyId);
    }

    @PostMapping("/is-banned")
    ResponseEntity<Boolean> isBannedFromParty(@RequestBody ClientTableDTO request){
        return this.service.isBannedFromParty(request);
    }

    @DeleteMapping("/{partyId}/client/{clientId}")
    void banClientFromParty(@PathVariable("clientId") Long clientId,@PathVariable("partyId")  Long partyId) {
        this.service.banClientFromParty(clientId, partyId);
    }

    @DeleteMapping("/{partyId}/close-if-inactive")
    boolean closePartyIfHaveNoClients(@PathVariable("partyId") Long partyId) {
        return this.service.closePartyIfHaveNoClients(partyId);
    }

    @DeleteMapping("/client/{client}/but/{partyId}")
    void logoutClientFromPartiesBut(@PathVariable("client") String client, @PathVariable("partyId") Long partyId){
        this.service.logoutClientFromPartiesBut(client,partyId);
    }

    @GetMapping("/leader/party/{partyId}")
    public Optional<ClientPartyDTO> findLeaderFromParty(@PathVariable("partyId") Long partyId){
        return this.service.findLeaderFromParty(partyId);
    }

    @GetMapping("/client/ip/{ip}")
    public Optional<ClientPartyDTO> findByIp(@PathVariable("ip") String ip){
        return this.service.findByIp(ip);
    }

    @GetMapping("/client/{clientId}/party/{partyId}")
    ClientPartyDTO findClientByClientIdAndPartyAndActive(@PathVariable("clientId") Long clientId, @PathVariable("partyId") Long partyId){
        return this.service.findClientByClientIdAndPartyAndActive(clientId, partyId);
    }
}
