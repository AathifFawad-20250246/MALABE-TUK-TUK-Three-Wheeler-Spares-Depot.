package com.example.malabetuktuk;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<CartItem> cart = new ArrayList<>();

    public void addItem(Part part, int qty) {

        if(qty <= 0){
            return;
        }

        if(qty > part.getQuantity()){
            return;
        }

        cart.add(new CartItem(part, qty));
    }

    public ArrayList<CartItem> getItems(){
        return cart;
    }

    public void clearCart(){
        cart.clear();
    }
}