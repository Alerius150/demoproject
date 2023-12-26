package com.example.demoproject.services.impl;


import com.example.demoproject.entities.Categories;
import com.example.demoproject.entities.Countries;
import com.example.demoproject.entities.ShopItems;
import com.example.demoproject.repositories.CategoryRepository;
import com.example.demoproject.repositories.CountryRepository;
import com.example.demoproject.repositories.ShopItemsRepository;
import com.example.demoproject.services.ItemService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemsRepository shopItemsRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemsRepository.save(item);
    }

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemsRepository.findAllByAmountGreaterThanOrderByPriceDesc(0);
    }

    @Override
    public ShopItems getItem(Long id) {
        return shopItemsRepository.findByIdAndAmountGreaterThan(id,0);
    }

    @Override
    public void deleteItem(ShopItems item) {
        shopItemsRepository.delete(item);
    }

    @Override
    public ShopItems saveItem(ShopItems item) {
        return shopItemsRepository.save(item);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Countries addCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries saveCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries getCountry(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Categories addCategory(Categories category) {
        return categoryRepository.save(category);
    }


}
