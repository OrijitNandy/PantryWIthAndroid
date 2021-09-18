package com.example.shopaid;

import java.util.Objects;

public class Pantry {
    private String id;
    private String name;
    private String price;
    private String quantity;
    private Integer[] imageId;
//    private int image;

    public Pantry(String id, String name,  String price, String quantity) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.price = price;
        this.quantity= quantity;
        this.imageId = imageId;
//        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {return quantity;}

    public Integer[] getImageId() {
        return imageId;
    }

}
