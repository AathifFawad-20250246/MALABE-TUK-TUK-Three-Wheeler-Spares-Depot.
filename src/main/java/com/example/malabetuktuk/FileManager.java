package com.example.malabetuktuk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    public static void readInventoryFile() {

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/data/inventory_legacy.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);

            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading inventory file.");
            e.printStackTrace();

        }

    }
}
