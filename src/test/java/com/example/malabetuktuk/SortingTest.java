package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class SortingTest {


    @Test
    public void testSortByPrice(){


        ArrayList<Double> prices =
                new ArrayList<>();


        prices.add(5000.0);
        prices.add(1000.0);
        prices.add(3000.0);


        Collections.sort(prices);


        assertEquals(
                1000,
                prices.get(0)
        );

    }

}
