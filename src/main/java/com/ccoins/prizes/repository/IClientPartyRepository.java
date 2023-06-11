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

    Optional<ClientParty> findByPartyAndClientAndActiveIsTrue(Long party, Long client);

    @Query(value = "select cp.* from clients_parties cp  " +
            " inner join  clients c on c.id = cp.FK_CLIENT " +
            " where c.ip = :client " +
            " and cp.ACTIVE is true", nativeQuery = true)
    List<ClientParty> findByIpAndActiveTrue(@Param("client") String client);

    @Query(value = "SELECT c.NICK_NAME FROM clients_parties cp " +
            "inner join clients c on c.id = cp.FK_CLIENT " +
            "where cp.id = :id", nativeQuery = true)
    Optional<String> findClientNameById(@Param("id") Long newLeaderId);


    @Query(value = "select * from clients_parties cp  " +
            "      inner join clients c on c.id = cp.FK_CLIENT  " +
            " where cp.FK_PARTY = (select max(p.id) from parties p " +
            " inner join bar_tables bt on bt.id = p.FK_TABLE " +
            "     where bt.CODE = :tableCode) " +
            " and c.ip = :clientIp" +
            " and cp.banned is true",nativeQuery = true)
    List<ClientParty> getBannedClientFromActualPartyByClientIpAndTableCode(@Param("clientIp") String clientIp, @Param("tableCode") String tableCode);

    @Query(value = "select cp.* from clients_parties cp " +
            "inner join clients c on c.id = cp.FK_CLIENT " +
            "where c.ip = :clientIp " +
            " and cp.ACTIVE is true", nativeQuery = true)
    Optional<ClientParty> findByIp(@Param("clientIp") String leaderId);

    Optional<ClientParty> findByPartyAndLeaderIsTrue(Long partyId);

    @Query(value = "select cp.* from clients_parties cp " +
            "inner join clients c on c.id = cp.FK_CLIENT " +
            "where c.id = :clientId " +
            " and cp.ACTIVE is true" +
            " and cp.FK_PARTY = :partyId", nativeQuery = true)
    Optional<ClientParty> findByClientIdAndPartyAndActive(@Param("clientId") Long clientId, @Param("partyId") Long partyId);
}
