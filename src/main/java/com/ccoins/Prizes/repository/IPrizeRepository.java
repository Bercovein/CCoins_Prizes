package com.ccoins.Prizes.repository;


import com.ccoins.Prizes.model.Prize;
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

    Optional<List<Prize>> findByBar(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Prizes set active = IF(active IS TRUE, FALSE, TRUE) where id = :id", nativeQuery = true)
    int updateActive(@Param("id") Long id);
}
