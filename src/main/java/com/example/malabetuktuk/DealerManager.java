package com.example.malabetuktuk;

import java.util.ArrayList;
import java.util.Collections;

public class DealerManager {

    private ArrayList<Dealer> dealerList;


    // Constructor
    public DealerManager() {

        dealerList = new ArrayList<>();

    }


    // Add dealer
    public void addDealer(Dealer dealer) {

        dealerList.add(dealer);

    }


    // Get all dealers
    public ArrayList<Dealer> getAllDealers() {

        return dealerList;

    }


    // Random select 4 dealers
    public ArrayList<Dealer> getRandomFourDealers() {


        ArrayList<Dealer> randomDealers =
                new ArrayList<>(dealerList);


        // Shuffle dealers randomly
        Collections.shuffle(randomDealers);


        // Return only 4 dealers
        if(randomDealers.size() >= 4) {

            return new ArrayList<>(
                    randomDealers.subList(0,4)
            );

        }


        return randomDealers;

    }

}
