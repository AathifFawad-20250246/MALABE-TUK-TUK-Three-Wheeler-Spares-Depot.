package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SearchTest {


    @Test
    public void testSearchPartName(){

        Part part = new Part(
                "P01",
                "Engine",
                "Toyota",
                5000,
                5,
                "Engine",
                "2026",
                ""
        );


        boolean result =
                part.getPartName()
                        .contains("Engine");


        assertTrue(result);

    }

}