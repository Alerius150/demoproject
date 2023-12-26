package com.example.demoproject.db;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.ArrayList;

public class DBManager {

    public static ArrayList<Items> items = new ArrayList<>();

    static {

        items.add(new Items(1L, "Iphone 11Pro", 400000));
        items.add(new Items(2L, "Xiaomi Redmi Note 9", 120000));
        items.add(new Items(3L, "Poco X3 Pro", 100000));
        items.add(new Items(4L, "Nokia 3310", 10000));

    }

    private static Long id=5L;

    public static ArrayList<Items> getItems(){
        return items;
    }

    public static void addItem(Items item){
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Items getItem(Long id){
        for(Items it : items){
            if(it.getId().equals(id)) return it;
        }
        return null;
    }





}
