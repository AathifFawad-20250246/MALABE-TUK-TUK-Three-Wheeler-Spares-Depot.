package com.example.malabetuktuk;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;


public class AuditLogger {


    private static final String FILE_NAME =
            "audit_log.txt";


    public static void log(String action){


        try(FileWriter writer =
                    new FileWriter(FILE_NAME, true)){


            writer.write(
                    LocalDateTime.now()
                            + " : "
                            + action
                            + "\n"
            );


        }catch(IOException e){

            e.printStackTrace();

        }

    }

}