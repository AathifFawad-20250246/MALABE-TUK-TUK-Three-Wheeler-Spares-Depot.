package com.example.malabetuktuk;

import java.io.FileWriter;
import java.io.IOException;

public class AuditLogger {

    public static void log(String message) {

        try {

            FileWriter writer = new FileWriter("audit_log.txt", true);

            writer.write(message + "\n");

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}