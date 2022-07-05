package com.ccoins.prizes.repository;


import com.ccoins.prizes.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IWinnerRepository extends JpaRepository<Winner, Long> {

}
