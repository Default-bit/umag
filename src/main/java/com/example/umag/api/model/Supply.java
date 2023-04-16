package com.example.umag.api.model;

import org.springframework.data.annotation.Id;

public class Supply {
    @Id
    private String id;
    private String barcode;
    private int quantity;
    private int price;
    private String supplyTime;


    public Supply(String barcode, int quantity, int price, String supplyTime){
        // this.id = id;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
        this.supplyTime = supplyTime;
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
        return supplyTime;
    }

    public void setTime(String time) {
        this.supplyTime = time;
    }

}
