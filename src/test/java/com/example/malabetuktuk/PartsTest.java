package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Part {

    @Test
    public void testPartCreation() {

        Part part = new Part(
                "P001",
                "Engine Oil",
                "Castrol",
                2500.00,
                15,
                "Engine",
                "2026-07-28",
                "oil.jpg"
        );

        assertEquals("P001", part.getPartCode());
        assertEquals("Engine Oil", part.getPartName());
        assertEquals("Castrol", part.getBrand());
        assertEquals(2500.00, part.getPrice());
        assertEquals(15, part.getQuantity());
        assertEquals("Engine", part.getCategory());
        assertEquals("2026-07-28", part.getDateAdded());
        assertEquals("oil.jpg", part.getImageName());
    }

    @Test
    public void testUpdatePartName() {

        Part part = new Part(
                "P002",
                "Brake Pad",
                "TVS",
                1200.00,
                10,
                "Brakes",
                "2026-07-28",
                "brake.jpg"
        );

        part.setPartName("Premium Brake Pad");

        assertEquals("Premium Brake Pad", part.getPartName());
    }

    @Test
    public void testUpdateBrand() {

        Part part = new Part(
                "P003",
                "Spark Plug",
                "NGK",
                850.00,
                20,
                "Electrical",
                "2026-07-28",
                "plug.jpg"
        );

        part.setBrand("Bosch");

        assertEquals("Bosch", part.getBrand());
    }

    @Test
    public void testUpdatePrice() {

        Part part = new Part(
                "P004",
                "Tyre",
                "CEAT",
                6500.00,
                8,
                "Bodywork",
                "2026-07-28",
                "tyre.jpg"
        );

        part.setPrice(7000.00);

        assertEquals(7000.00, part.getPrice());
    }

    @Test
    public void testUpdateQuantity() {

        Part part = new Part(
                "P005",
                "Battery",
                "Exide",
                9500.00,
                5,
                "Electrical",
                "2026-07-28",
                "battery.jpg"
        );

        part.setQuantity(12);

        assertEquals(12, part.getQuantity());
    }

    @Test
    public void testUpdateCategory() {

        Part part = new Part(
                "P006",
                "Fuel Pump",
                "Toyota",
                4500.00,
                7,
                "Engine",
                "2026-07-28",
                "pump.jpg"
        );

        part.setCategory("Fuel System");

        assertEquals("Fuel System", part.getCategory());
    }

    @Test
    public void testToString() {

        Part part = new Part(
                "P007",
                "Headlight",
                "Lucas",
                1800.00,
                9,
                "Electrical",
                "2026-07-28",
                "light.jpg"
        );

        String expected =
                "P007 | Headlight | Lucas | Rs.1800.0 | Qty: 9 | Electrical";

        assertEquals(expected, part.toString());
    }

}