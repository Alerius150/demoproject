package com.example.demoproject.services;


import com.example.demoproject.entities.Categories;
import com.example.demoproject.entities.Countries;
import com.example.demoproject.entities.ShopItems;

import java.util.List;

public interface ItemService {


    public ShopItems addItem(ShopItems item);

    List<ShopItems> getAllItems();

    ShopItems getItem(Long id);

    void deleteItem(ShopItems item);

    ShopItems saveItem(ShopItems item);

    List<Countries> getAllCountries();
    Countries addCountry(Countries country);
    Countries saveCountry(Countries country);
    Countries getCountry(Long id);

    List<Categories> getAllCategories();

    Categories getCategory(Long id);

    Categories saveCategory(Categories category);

    Categories addCategory(Categories category);



}
