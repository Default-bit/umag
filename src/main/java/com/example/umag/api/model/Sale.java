package com.example.umag.api.model;

import org.springframework.data.annotation.Id;

public class Sale {
    @Id
    private String id;
    private String barcode;
    private int quantity;
    private int price;
    private String saleTime;

    public Sale(String barcode, int quantity, int price, String saleTime){
        // this.id = id;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
        this.saleTime = saleTime;
    }

    public String getId(){
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTime() {
        return saleTime;
    }

    public void setTime(String saleTime) {
        this.saleTime = saleTime;
    }
}
