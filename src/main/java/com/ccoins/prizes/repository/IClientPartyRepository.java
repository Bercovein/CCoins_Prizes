package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.ClientParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IClientPartyRepository extends JpaRepository<ClientParty, Long> {

}
