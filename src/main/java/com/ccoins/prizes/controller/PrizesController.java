package com.ccoins.prizes.controller;

import com.ccoins.prizes.dto.GenericRsDTO;
import com.ccoins.prizes.dto.ListDTO;
import com.ccoins.prizes.dto.PrizeDTO;
import com.ccoins.prizes.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/prizes")
public class PrizesController {

    private final IPrizeService service;

    @Autowired
    public PrizesController(IPrizeService service) {
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
