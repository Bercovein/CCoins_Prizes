package com.ccoins.Prizes.repository;


import com.ccoins.Prizes.model.Party;
import com.ccoins.Prizes.model.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPartyRepository extends JpaRepository<Party, Long> {

}
