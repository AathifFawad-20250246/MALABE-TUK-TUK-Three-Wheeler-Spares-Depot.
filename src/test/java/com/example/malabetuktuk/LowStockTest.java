package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LowStockTest {


    @Test
    public void testLowStock(){


        Part part = new Part(
                "P01",
                "Brake",
                "Honda",
                2000,
                5,
                "Engine",
                "2026",
                ""
        );


        int threshold = 10;


        boolean lowStock =
                part.getQuantity() < threshold;


        assertTrue(lowStock);

    }

}