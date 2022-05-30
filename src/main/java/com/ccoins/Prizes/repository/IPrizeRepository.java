package com.ccoins.Prizes.repository;


import com.ccoins.Prizes.model.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IPrizeRepository extends JpaRepository<Prize, Long> {

    Optional<List<Prize>> findByBar(Long id);

    @Modifying
    @Query("UPDATE Bar set active = IF(active IS TRUE, FALSE, TRUE) where id = :id")
    int updateActive(@Param("id") Long id);
}
