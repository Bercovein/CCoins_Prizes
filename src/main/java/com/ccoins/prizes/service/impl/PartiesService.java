package com.ccoins.prizes.service.impl;

import com.ccoins.prizes.dto.PartyDTO;
import com.ccoins.prizes.exceptions.BadRequestException;
import com.ccoins.prizes.exceptions.constant.ExceptionConstant;
import com.ccoins.prizes.model.Party;
import com.ccoins.prizes.model.projection.IPParty;
import com.ccoins.prizes.repository.IPartyRepository;
import com.ccoins.prizes.service.IPartiesService;
import com.ccoins.prizes.service.IRandomNameService;
import com.ccoins.prizes.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class PartiesService implements IPartiesService {

    private final IPartyRepository repository;

    private final IRandomNameService randomizer;

    @Autowired
    public PartiesService(IPartyRepository repository, IRandomNameService randomizer) {
        this.repository = repository;
        this.randomizer = randomizer;
    }

    @Override
    public Optional<IPParty> findActivePartyByTable(Long id) {
        return this.repository.findByTableAndActive(id,true);
    }

    @Override
    public PartyDTO createByTable(Long id) {

        PartyDTO response;

        try {
            Party party = Party.builder()
                    .active(true)
                    .table(id)
                    .name(this.randomizer.randomName())
                    .startDate(LocalDateTime.now())
                    .build();

            party = this.repository.save(party);

            response = (PartyDTO) MapperUtils.map(party, PartyDTO.class);
        }catch (Exception e) {
            throw new BadRequestException(ExceptionConstant.PARTY_CREATE_ERROR_CODE,
                    this.getClass(), ExceptionConstant.PARTY_CREATE_ERROR);
        }

        return response;

    }
}
