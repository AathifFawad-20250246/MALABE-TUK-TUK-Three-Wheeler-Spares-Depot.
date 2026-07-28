package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileParserTest {


    @Test
    public void testMixedDelimiterParsing(){

        String line =
                "P001|Engine Oil|Toyota|1000|5|Engine";


        String[] data =
                line.split("\\|");


        assertEquals(
                "P001",
                data[0]
        );


        assertEquals(
                "Engine Oil",
                data[1]
        );

    }


    @Test
    public void testMissingField(){

        String line =
                "P002|Brake|Honda";


        String[] data =
                line.split("\\|");


        assertTrue(
                data.length < 6
        );

    }

}