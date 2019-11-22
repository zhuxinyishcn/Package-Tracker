package edu.unl.cse.csce361.package_tracker.backend;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseInfo {
    String USERNAME;
    String PASSWORD;
    String URL;

    public DatabaseInfo () {

        try {
            Scanner sc = new Scanner(new File("src/main/resources/database.info"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.contains("username")) {
                    String []token= line.split(":");
                    this.USERNAME = token[1].trim();
                } else if (line.contains("password")) {
                    String []token= line.split(":");
                    this.PASSWORD = token[1].trim();
                } else if (line.contains("url")) {
                    String []token= line.split(":",2);
                    this.URL = token[1].trim();
                }

            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("file didn't found");
        }
    }
}

