package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.Party;
import com.ccoins.prizes.model.projection.IPParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
