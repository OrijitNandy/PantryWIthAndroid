package com.example.shopaid;

import java.util.Objects;

public class Shoplist {

    private String shopId;
    private String shopName;
    private String shopLoc;
    private String date;
//    private int image;

    public Shoplist(String id, String name,  String location, String date) {
        this.shopId = id;
        this.shopName = Objects.requireNonNull(name);
        this.shopLoc = location;
        this.date= date;
//        this.image = image;
    }

    public String getShopId() {
        return  shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getshopLoc() {
        return shopLoc;
    }

    public String getDate() {return date;}
}
