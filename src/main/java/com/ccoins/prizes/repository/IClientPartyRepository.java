package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.ClientParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IClientPartyRepository extends JpaRepository<ClientParty, Long> {

    List<ClientParty> findByParty(Long id);

    List<ClientParty> findByClientAndActive(String client, boolean b);

    @Query(value = "SELECT c.NICK_NAME FROM clients_parties cp " +
            "inner join clients c on c.id = cp.FK_CLIENT " +
            "where c.id = :id", nativeQuery = true)
    Optional<String> findClientNameById(@Param("id") Long newLeaderId);
}
