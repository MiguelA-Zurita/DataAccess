package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the path of origin of the file you want to copy: ");
        String origin = input.nextLine();
        System.out.print("Please enter the path of the file you want to paste to: ");
        String destiny = input.nextLine();
        if (origin.endsWith(".xml") || origin.endsWith(".txt")) {
            try (FileReader originFile = new FileReader(origin);
                 FileWriter destinyFile = new FileWriter(destiny)) {
                while(originFile.ready()) {
                    destinyFile.write(originFile.read());
                }
            } catch (Exception e) {
                System.out.println("Origin file not found");
            }
        } else {
            try(FileInputStream originFile = new FileInputStream(origin);
            FileOutputStream destinyFile = new FileOutputStream(destiny)) {
                destinyFile.write(originFile.readAllBytes());
            } catch (Exception e) {
                System.out.println("Origin file not found");
            }
        }
    }
}