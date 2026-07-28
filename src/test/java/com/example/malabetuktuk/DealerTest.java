package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DealerTest {


    @Test
    public void testDealerSelection(){


        Dealer dealer =
                new Dealer(
                        "D001",
                        "ABC Motors",
                        "0771234567",
                        "Colombo"
                );


        assertEquals(
                "ABC Motors",
                dealer.getDealerName()
        );

    }

}