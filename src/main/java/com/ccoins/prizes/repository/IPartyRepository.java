package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.Party;
import com.ccoins.prizes.model.projection.IPParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IPartyRepository extends JpaRepository<Party, Long> {

    Optional<IPParty> findByTable(Long id);
}
