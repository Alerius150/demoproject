package com.example.demoproject.repositories;


import com.example.demoproject.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ShopItemsRepository extends JpaRepository<ShopItems, Long> {

    List<ShopItems> findAllByAmountGreaterThanOrderByPriceDesc(int amount);
    ShopItems findByIdAndAmountGreaterThan(Long id, int amount);





}
