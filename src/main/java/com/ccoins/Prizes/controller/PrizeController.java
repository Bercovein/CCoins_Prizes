package com.ccoins.Prizes.controller;

import com.ccoins.Prizes.dto.GenericRsDTO;
import com.ccoins.Prizes.dto.ListDTO;
import com.ccoins.Prizes.dto.PrizeDTO;
import com.ccoins.Prizes.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/prizes")
public class PrizeController {

    private final IPrizeService service;

    @Autowired
    public PrizeController(IPrizeService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<PrizeDTO> saveOrUpdate(@RequestBody PrizeDTO request){
        return this.service.saveOrUpdate(request);
    }

    @GetMapping({"/owner/{barId}","/owner/{barId}/{status}"})
    ResponseEntity<ListDTO> findAllByBar(@PathVariable("barId") Long id, @PathVariable("status") Optional<String> status) {
        return this.service.findAllByBarAndOptStatus(id,status);
    }

    @GetMapping("/{id}")
    ResponseEntity<PrizeDTO> findById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PutMapping("/{id}/active")
    ResponseEntity<PrizeDTO> active(@PathVariable("id") Long id){
        return this.service.active(id);
    }

    @PutMapping
    ResponseEntity<GenericRsDTO> activeByList(@RequestBody ListDTO request){
        return this.service.activeByList(request);
    }
}
