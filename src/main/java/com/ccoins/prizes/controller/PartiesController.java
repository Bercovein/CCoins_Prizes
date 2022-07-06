package com.ccoins.prizes.controller;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.service.IPartiesService;
import com.ccoins.prizes.service.IRandomNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    Optional<IPParty> findActivePartyByTable(Long id){
        return this.service.findActivePartyByTable(id);
    }

    @PostMapping("")
    PartyDTO createParty(Long id){
        return this.service.createByTable(id);
    }

}
