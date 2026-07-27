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
}