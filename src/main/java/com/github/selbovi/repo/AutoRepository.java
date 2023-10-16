package com.github.selbovi.repo;

import com.github.selbovi.domain.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {

    Auto findAutoByModel(String model);
}
