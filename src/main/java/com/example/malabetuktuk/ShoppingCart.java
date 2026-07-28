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

    public double getTotal(){

        double total = 0;


        for(CartItem item : cart){

            total += item.getSubtotal();

        }

        return total;

    }

    public double calculateDiscount(double discountPercent) {

        double total = getTotal();

        double discountAmount = total * (discountPercent / 100);

        return total - discountAmount;
    }

    public double calculateCheckoutTotal(){

        double total = 0;

        boolean hasEngine = false;
        boolean hasElectrical = false;


        for(CartItem item : cart){

            // Item-level bulk discount
            double itemTotal = item.getSubtotal();


            if(item.getQuantity() >= 3){

                itemTotal = itemTotal * 0.95;

            }


            total += itemTotal;


            String category =
                    item.getPart().getCategory();


            if(category.equalsIgnoreCase("Engine")){

                hasEngine = true;

            }


            if(category.equalsIgnoreCase("Electrical")){

                hasElectrical = true;

            }

        }


        // Cart-level synergy discount
        if(hasEngine && hasElectrical){

            total = total * 0.90;

        }


        return total;

    }

    public void clearCart(){
        cart.clear();
    }
}