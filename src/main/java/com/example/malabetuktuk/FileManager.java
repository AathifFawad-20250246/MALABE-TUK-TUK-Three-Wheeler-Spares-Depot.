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

                line = line.replace("|", ",");
                line = line.replace(";", ",");

                String[] data = line.split(",");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }

                System.out.println("Fields: " + data.length);

                if (data.length >= 7) {

                    String partCode = data[0];
                    String partName = data[1];
                    String brand = data[2];

                    // Remove "Rs." and spaces from the price
                    String priceText = data[3]
                            .replace("Rs.", "")
                            .replace("Rs", "")
                            .trim();

                    double price = 0;

                    try {
                        price = Double.parseDouble(priceText);
                    } catch (NumberFormatException e) {
                        price = 0;
                    }

                    int quantity = Integer.parseInt(data[4].trim());

                    String category = data[5];

                    String dateAdded = data[6];

                    String imageName = "";

                    if (data.length >= 8) {
                        imageName = data[7];
                    }

                    Part part = new Part(
                            partCode,
                            partName,
                            brand,
                            price,
                            quantity,
                            category,
                            dateAdded,
                            imageName
                    );

                    partList.add(part);

                    System.out.println(part);
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading inventory file.");
            e.printStackTrace();

        }

        return partList;

    }

    public static ArrayList<Dealer> readDealerFile() {

        ArrayList<Dealer> dealerList = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/data/dealers_legacy.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.replace("|", ",");
                line = line.replace(";", ",");

                String[] data = line.split(",");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }

                String dealerId = data[0];
                String dealerName = data[1];

                String phone = "";
                String location = "";

                if (data.length >= 4) {
                    phone = data[2];
                    location = data[3];
                }

                Dealer dealer = new Dealer(
                        dealerId,
                        dealerName,
                        phone,
                        location
                );

                dealerList.add(dealer);

                System.out.println(dealer);

            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return dealerList;
    }

    public static void saveDealerFile(java.util.List<Dealer> dealers) {

        try {

            java.io.PrintWriter writer =
                    new java.io.PrintWriter("src/main/resources/data/dealers_legacy.txt");

            for (Dealer dealer : dealers) {

                writer.println(
                        dealer.getDealerId() + "," +
                                dealer.getDealerName() + "," +
                                dealer.getPhone() + "," +
                                dealer.getLocation()
                );

            }

            writer.close();

            System.out.println("Dealer file saved.");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
