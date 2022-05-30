package com.ccoins.Prizes.controller;

import com.ccoins.Prizes.dto.ListDTO;
import com.ccoins.Prizes.dto.PrizeDTO;
import com.ccoins.Prizes.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/owner/{barId}")
    ResponseEntity<ListDTO> findAllByBar(@PathVariable("barId") Long id) {
        return this.service.findAllByBar(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<PrizeDTO> findById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PutMapping("/{id}/active")
    ResponseEntity<PrizeDTO> active(@PathVariable("id") Long id){
        return this.service.active(id);
    }
}
