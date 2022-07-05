package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.Prize;
import com.ccoins.prizes.model.projection.IPPrize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface IPrizeRepository extends JpaRepository<Prize, Long> {

    Optional<List<IPPrize>> findByBar(Long id);

    Optional<List<IPPrize>> findByBarAndActive(Long bar, boolean state);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Prizes set active = IF(active IS TRUE, FALSE, TRUE) where id = :id", nativeQuery = true)
    int updateActive(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Prizes set active = IF(active IS TRUE, FALSE, TRUE) where id in (:list)",nativeQuery = true)
    int updateActiveList(@Param("list") List<Long> list);

    Optional<List<Prize>> findByIdIn(List<Long> list);
}
