package com.github.selbovi.repo;

import com.github.selbovi.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    Buyer findBuyerByFio(String fio);

}
