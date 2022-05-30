package com.ccoins.Prizes.service;

import com.ccoins.Prizes.dto.ListDTO;
import com.ccoins.Prizes.dto.PrizeDTO;
import org.springframework.http.ResponseEntity;

public interface IPrizeService {
    ResponseEntity<PrizeDTO> saveOrUpdate(PrizeDTO request);

    ResponseEntity<ListDTO> findAllByBar(Long id);

    ResponseEntity<PrizeDTO> findById(Long id);

    ResponseEntity<PrizeDTO> active(Long id);
}
