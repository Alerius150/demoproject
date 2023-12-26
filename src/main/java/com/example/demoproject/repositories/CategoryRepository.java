package com.example.demoproject.repositories;


import com.example.demoproject.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
