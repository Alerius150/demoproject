package com.example.demoproject.repositories;

import com.example.demoproject.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Countries, Long> {




}
