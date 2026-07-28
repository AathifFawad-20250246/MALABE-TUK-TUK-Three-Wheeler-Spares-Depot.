package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ShoppingCartTest {


    @Test
    public void testEmptyCartTotal(){

        ShoppingCart cart = new ShoppingCart();

        double total = cart.getTotal();


        assertEquals(0, total);

    }



    @Test
    public void testBulkDiscount(){

        Part engine = new Part(
                "P001",
                "Engine Oil",
                "Toyota",
                1000,
                10,
                "Engine",
                "2026-07-28",
                "engine.jpg"
        );


        ShoppingCart cart = new ShoppingCart();


        // Add 3 units
        cart.addItem(engine, 3);


        double total =
                cart.calculateCheckoutTotal();


        // 1000 x 3 = 3000
        // 5% discount = 2850

        assertEquals(
                2850,
                total
        );

    }




    @Test
    public void testSynergyDiscount(){

        Part engine = new Part(
                "P001",
                "Engine",
                "Honda",
                5000,
                10,
                "Engine",
                "2026-07-28",
                "engine.jpg"
        );


        Part battery = new Part(
                "P002",
                "Battery",
                "Bosch",
                2000,
                10,
                "Electrical",
                "2026-07-28",
                "battery.jpg"
        );


        ShoppingCart cart = new ShoppingCart();


        cart.addItem(engine,1);

        cart.addItem(battery,1);


        double total =
                cart.calculateCheckoutTotal();


        // 5000 + 2000 = 7000
        // 10% synergy discount = 6300

        assertEquals(
                6300,
                total
        );

    }




    @Test
    public void testManualDiscount(){

        ShoppingCart cart = new ShoppingCart();


        Part part = new Part(
                "P003",
                "Brake",
                "Honda",
                100,
                5,
                "Other",
                "2026-07-28",
                "brake.jpg"
        );


        cart.addItem(part,1);


        double result =
                cart.calculateDiscount(10);


        // 100 - 10% = 90

        assertEquals(
                90,
                result
        );

    }

}