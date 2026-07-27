package com.example.malabetuktuk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<Part> readInventoryFile() {
        ArrayList<Part> partList = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/data/inventory_legacy.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println("Reading: " + line);

                String[] data;

                if (line.contains("|")) {
                    data = line.split("\\|");
                } else if (line.contains(";")) {
                    data = line.split(";");
                } else {
                    data = line.split(",");
                }

                System.out.println("Fields: " + data.length);

                if (data.length >= 8) {

                    String partCode = data[0].trim();
                    String partName = data[1].trim();
                    String brand = data[2].trim();

                    System.out.println("Code : " + partCode);
                    System.out.println("Name : " + partName);
                }

            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading inventory file.");
            e.printStackTrace();

        }

        return partList;

    }
}
