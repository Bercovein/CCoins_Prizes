package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.ClientParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IClientPartyRepository extends JpaRepository<ClientParty, Long> {

    List<ClientParty> findByParty(Long id);

    List<ClientParty> findByClientAndActive(String client, boolean b);
}
