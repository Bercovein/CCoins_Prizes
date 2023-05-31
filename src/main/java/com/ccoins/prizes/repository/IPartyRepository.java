package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.Party;
import com.ccoins.prizes.model.projection.IPParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface IPartyRepository extends JpaRepository<Party, Long> {
    Optional<IPParty> findByTableAndActive(Long id, boolean b);
    Optional<Party> findById(Long id);
    Party save(Party party);

    @Query(value = "SELECT P.* FROM parties P " +
            "INNER JOIN bar_tables T ON P.FK_TABLE = T.ID " +
            "WHERE T.CODE = :code " +
            "AND P.ACTIVE <> 0", nativeQuery = true)
    Optional<Party> findByTableCode(@Param("code") String code);

    @Query(value = "select p.id from parties p " +
            "inner join clients_parties cp on cp.FK_PARTY = p.id " +
            "where cp.FK_CLIENT IN (:list) " +
            "group by p.id;",nativeQuery = true)
    Optional<List<Long>> findAllPartyIdIn(@Param("list") List<Long> list);


    @Query(value = "update parties p  " +
            "inner join clients_parties cp on cp.fk_party = p.id  " +
            "set p.active = 0  " +
            "where p.id = :partyId " +
            "and 0 = (select count(cpi.fk_client) from clients_parties cpi  " +
            "where cpi.fk_party = :partyId  " +
            "and cpi.active = 1)",nativeQuery = true)
    @Transactional
    @Modifying
    Long closePartyIfHaveNoClients(@Param("partyId") Long partyId);

    @Query(value = "select cp.leader from clients_parties cp " +
            "inner join clients c on c.id = cp.FK_CLIENT " +
            "where c.ip = :leaderIp " +
            "and fk_party = :partyId " +
            "and cp.active = 1",nativeQuery = true)
    Integer isLeaderFromParty(@Param("leaderIp") String leaderIp, @Param("partyId") Long partyId);

    @Query(value = "update clients_parties " +
            "set banned = 1 " +
            "where fk_client = :clientId " +
            "and fk_party = :partyId", nativeQuery = true)
    @Transactional
    @Modifying
    void banClientFromParty(@Param("clientId") Long clientId, @Param("partyId") Long partyId);

    @Query(value = "select p.* from parties p " +
            "inner join bar_tables bt on bt.ID = p.FK_TABLE " +
            "where bt.FK_BAR = :barId" +
            " and p.active is true", nativeQuery = true)
    Optional<List<Party>> findActivePartiesByBar(@Param("barId") Long barId);
}
