package com.example.malabetuktuk;

import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Part> partList;

    public InventoryManager() {
        partList = new ArrayList<>();
    }

    public void addPart(Part part) {
        partList.add(part);
    }

    public ArrayList<Part> getAllParts() {
        return partList;
    }

    public void displayParts() {
        for (Part part : partList) {
            System.out.println(part);
        }
    }


    // Multi Criteria Search (3 Filters)
    public ArrayList<Part> multiCriteriaSearch(
            String name,
            String category,
            double maxPrice) {

        ArrayList<Part> results = new ArrayList<>();

        for (Part part : partList) {

            boolean matchName = name.isEmpty() ||
                    part.getPartName()
                            .toLowerCase()
                            .contains(name.toLowerCase());


            boolean matchCategory = category.isEmpty() ||
                    part.getCategory()
                            .equalsIgnoreCase(category);


            boolean matchPrice = part.getPrice() <= maxPrice;


            if(matchName && matchCategory && matchPrice) {
                results.add(part);
            }
        }

        return results;
    }
}