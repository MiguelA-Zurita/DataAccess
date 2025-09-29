package org.example;

import java.io.*; //import libraries
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //Create a Scanner to read user's input
        System.out.print("Please enter the path of origin of the file you want to copy: ");
        String origin = input.nextLine(); // Read user's input
        System.out.print("Please enter the path of the file you want to paste to: ");
        String destiny = input.nextLine(); // Read user's input
        if (origin.endsWith(".xml") || origin.endsWith(".txt")) { //if the file is a xml or txt, work with FileReader and FileWriter
            try (FileReader originFile = new FileReader(origin);
                 FileWriter destinyFile = new FileWriter(destiny)) { //try catch in case any paths are not valid
                while(originFile.ready()) { //While the file is ready to be red, read
                    destinyFile.write(originFile.read());
                }
            } catch (Exception e) {
                System.out.println("Origin file not found or destiny path not found"); //Message to let know there's been an error
            } finally {
                System.out.println("File copied successfully");
            }
        } else {
            try(FileInputStream originFile = new FileInputStream(origin);
            FileOutputStream destinyFile = new FileOutputStream(destiny)) { //try catch in case any paths are invalid
                destinyFile.write(originFile.readAllBytes()); //Reads all bytes and after writes the given byte array
            } catch (Exception e) {
                System.out.println("Origin file not found or destiny path not found"); //Message to let know there's been an error
            } finally{
                System.out.println("File copied successfully");
            }
        }
    }
}