package com.ccoins.Prizes.service;

import com.ccoins.Prizes.dto.GenericRsDTO;
import com.ccoins.Prizes.dto.ListDTO;
import com.ccoins.Prizes.dto.PrizeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IPrizeService {
    ResponseEntity<PrizeDTO> saveOrUpdate(PrizeDTO request);

    ResponseEntity<ListDTO> findAllByBarAndOptStatus(Long id, Optional<String> status);

    ResponseEntity<PrizeDTO> findById(Long id);

    ResponseEntity<PrizeDTO> active(Long id);

    ResponseEntity<GenericRsDTO> activeByList(ListDTO request);

    ResponseEntity<GenericRsDTO> findByIdIn(List<Long> list);
}
