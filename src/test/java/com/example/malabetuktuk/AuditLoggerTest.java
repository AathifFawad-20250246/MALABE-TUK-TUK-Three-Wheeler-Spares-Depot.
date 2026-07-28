package com.example.malabetuktuk;

import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


public class AuditLoggerTest {


    @Test
    public void testAuditLogCreated(){


        AuditLogger.log(
                "JUnit Test Log"
        );


        File file =
                new File("audit_log.txt");


        assertTrue(
                file.exists()
        );

    }

}