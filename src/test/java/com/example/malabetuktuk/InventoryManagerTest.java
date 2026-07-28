package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {

    @Test
    public void testAddPart() {

        InventoryManager manager = new InventoryManager();

        Part part = new Part(
                "P001",
                "Engine Oil",
                "Castrol",
                2500.0,
                10,
                "Engine",
                "2026-07-28",
                "oil.jpg"
        );

        manager.addPart(part);

        assertEquals(1, manager.getAllParts().size());
    }

    @Test
    public void testSearchByName() {

        InventoryManager manager = new InventoryManager();

        manager.addPart(new Part(
                "P001",
                "Engine Oil",
                "Castrol",
                2500.0,
                10,
                "Engine",
                "2026-07-28",
                "oil.jpg"
        ));

        ArrayList<Part> results =
                manager.multiCriteriaSearch(
                        "Engine",
                        "",
                        10000
                );

        assertEquals(1, results.size());
    }

    @Test
    public void testSearchByCategory() {

        InventoryManager manager = new InventoryManager();

        manager.addPart(new Part(
                "P002",
                "Brake Pad",
                "TVS",
                1200.0,
                20,
                "Brakes",
                "2026-07-28",
                "brake.jpg"
        ));

        ArrayList<Part> results =
                manager.multiCriteriaSearch(
                        "",
                        "Brakes",
                        10000
                );

        assertEquals(1, results.size());
    }

    @Test
    public void testSearchByPrice() {

        InventoryManager manager = new InventoryManager();

        manager.addPart(new Part(
                "P003",
                "Spark Plug",
                "NGK",
                850.0,
                25,
                "Electrical",
                "2026-07-28",
                "plug.jpg"
        ));

        ArrayList<Part> results =
                manager.multiCriteriaSearch(
                        "",
                        "",
                        1000
                );

        assertEquals(1, results.size());
    }

    @Test
    public void testMultiCriteriaSearch() {

        InventoryManager manager = new InventoryManager();

        manager.addPart(new Part(
                "P004",
                "Engine Filter",
                "Toyota",
                3000.0,
                8,
                "Engine",
                "2026-07-28",
                "filter.jpg"
        ));

        ArrayList<Part> results =
                manager.multiCriteriaSearch(
                        "Filter",
                        "Engine",
                        5000
                );

        assertEquals(1, results.size());
    }

    @Test
    public void testNoResultsFound() {

        InventoryManager manager = new InventoryManager();

        manager.addPart(new Part(
                "P005",
                "Tyre",
                "CEAT",
                6500.0,
                15,
                "Bodywork",
                "2026-07-28",
                "tyre.jpg"
        ));

        ArrayList<Part> results =
                manager.multiCriteriaSearch(
                        "Battery",
                        "Electrical",
                        1000
                );

        assertTrue(results.isEmpty());
    }

}