package com.ccoins.Prizes.repository;


import com.ccoins.Prizes.model.Prize;
import com.ccoins.Prizes.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IWinnerRepository extends JpaRepository<Winner, Long> {

}
