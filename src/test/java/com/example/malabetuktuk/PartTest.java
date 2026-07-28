package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartTest {

    @Test
    public void testPartCreation() {

        Part part = new Part(
                "P001",
                "Engine Oil",
                "Castrol",
                2500,
                20,
                "Engine",
                "2026-07-28",
                "oil.jpg"
        );

        assertEquals("P001", part.getPartCode());
        assertEquals("Engine Oil", part.getPartName());
        assertEquals(2500, part.getPrice());
        assertEquals(20, part.getQuantity());
    }

    @Test
    public void testUpdatePrice() {

        Part part = new Part(
                "P002",
                "Brake Pad",
                "TVS",
                1500,
                10,
                "Brakes",
                "2026-07-28",
                "brake.jpg"
        );

        part.setPrice(1800);

        assertEquals(1800, part.getPrice());
    }

}