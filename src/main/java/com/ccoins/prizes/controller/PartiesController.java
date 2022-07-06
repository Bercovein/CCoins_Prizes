package com.ccoins.prizes.controller;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.service.IPartiesService;
import com.ccoins.prizes.service.IRandomNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/parties")
public class PartiesController {

    private final IPartiesService service;

    private final IRandomNameService  random;

    @Autowired
    public PartiesController(IPartiesService service, IRandomNameService random) {
        this.service = service;
        this.random = random;
    }

    @GetMapping("/table/{id}")
    Optional<IPParty> findActivePartyByTable(@PathVariable("id") Long id){
        return this.service.findActivePartyByTable(id);
    }

    @PostMapping("/table/{id}")
    PartyDTO createParty(@PathVariable("id")Long id){
        return this.service.createByTable(id);
    }

}
