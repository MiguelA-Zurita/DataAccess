package org.example;

/*
 * Console entry point for the SQLite CRUD demo.
 *
 * It provides a simple text-based menu to manage:
 * - Customers (CRUD)
 * - Items (CRUD)
 * - Sales (CRUD)
 * - Items-Sales (link items to a sale; manage quantities and list items per sale)
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Initializes the DB and starts the interactive menu loop.
     */
    public static void main(String[] args) {
        Database.init();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("----- SQLite Demo CRUD -----");
            System.out.println("1. Customers");
            System.out.println("2. Items");
            System.out.println("3. Sales");
            System.out.println("4. Items-Sales (link items to a sale)");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String opt = sc.nextLine().trim();
            try {
                switch (opt) {
                    case "1" -> customerMenu(sc);
                    case "2" -> itemMenu(sc);
                    case "3" -> saleMenu(sc);
                    case "4" -> itemSaleMenu(sc);
                    case "0" -> exit = true;
                    default -> System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Bye!");
    }


    //Customers sub-menu. Allows adding, listing, updating and deleting customers.

    private static void customerMenu(Scanner sc) throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("--- Customers ---");
            System.out.println("1. Add");
            System.out.println("2. List");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.print("DNI (9 chars): ");
                    String dni = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("First surname: ");
                    String fs = sc.nextLine().trim();
                    System.out.print("Last surname: ");
                    String ls = sc.nextLine().trim();
                    CustomerClass.add(new CustomerClass.Customer(dni, name, fs, ls));
                    System.out.println("Added");
                }
                case "2" -> {
                    List<CustomerClass.Customer> list = CustomerClass.getAll();
                    list.forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("DNI to update: ");
                    String dni = sc.nextLine().trim();
                    CustomerClass.Customer c = CustomerClass.getByDni(dni);
                    if (c == null) {
                        System.out.println("Not found");
                    } else{
                    System.out.print("New Name ("+ c.name() +"): ");
                    String name = emptyOr(sc.nextLine(), c.name());
                    System.out.print("New First surname ("+ c.firstSurname() +"): ");
                    String fs = emptyOr(sc.nextLine(), c.firstSurname());
                    System.out.print("New Last surname ("+ c.lastSurname() +"): ");
                    String ls = emptyOr(sc.nextLine(), c.lastSurname());
                    c = new CustomerClass.Customer(dni, name, fs, ls);
                    CustomerClass.update(c);
                    System.out.println("Updated");
                    }
                }
                case "4" -> {
                    System.out.print("DNI to delete: "); String dni = sc.nextLine().trim();
                    CustomerClass.delete(dni);
                    System.out.println("Deleted");
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }


    //Items sub-menu. Allows adding, listing, updating and deleting items.

    private static void itemMenu(Scanner sc) throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("--- Items ---");
            System.out.println("1. Add");
            System.out.println("2. List");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.print("Name: "); String name = sc.nextLine().trim();
                    System.out.print("Unit price: "); double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock: "); int stock = Integer.parseInt(sc.nextLine());
                    ItemClass.add(new ItemClass.Item(0, price, name, stock));
                    System.out.println("Added.\n");
                }
                case "2" -> ItemClass.getAll().forEach(System.out::println);
                case "3" -> {
                    System.out.print("ID to update: "); int id = Integer.parseInt(sc.nextLine());
                    ItemClass.Item it = ItemClass.getById(id);
                    if (it == null) {
                        System.out.println("Not found\n");
                    } else {
                        System.out.print("New name (" + it.name() + "): ");
                        String name = emptyOr(sc.nextLine(), it.name());
                        System.out.print("New unit price (" + it.unitPrice() + "): ");
                        String sp = sc.nextLine();
                        double price = sp.isBlank() ? it.unitPrice() : Double.parseDouble(sp);
                        System.out.print("New stock (" + it.stock() + "): ");
                        String ss = sc.nextLine();
                        int stock = ss.isBlank() ? it.stock() : Integer.parseInt(ss);
                        ItemClass.update(new ItemClass.Item(id, price, name, stock));
                        System.out.println("Updated.\n");
                    }
                }
                case "4" -> {
                    System.out.print("ID to delete: "); int id = Integer.parseInt(sc.nextLine());
                    ItemClass.delete(id);
                    System.out.println("Deleted.");
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }


    //Sales sub-menu. Allows adding, listing, updating and deleting sales.

    private static void saleMenu(Scanner sc) throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("--- Sales ---");
            System.out.println("1. Add");
            System.out.println("2. List");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.print("Sales date (YYYY-MM-DD): ");
                    LocalDate d = LocalDate.parse(sc.nextLine().trim());
                    System.out.print("DNI member (empty for null): ");
                    String dni = sc.nextLine().trim();
                    System.out.print("Total price: ");
                    double tp = Double.parseDouble(sc.nextLine());
                    Integer id = SaleClass.add(new SaleClass.Sale(0, tp, d, dni.isBlank()? null : dni));
                    System.out.println("Added sale with ID: " + id + "\n");
                }
                case "2" -> SaleClass.getAll().forEach(System.out::println); //
                case "3" -> { //Update sale
                    System.out.print("Sale ID to update: "); int id = Integer.parseInt(sc.nextLine());
                    SaleClass.Sale s = SaleClass.getById(id);
                    if (s == null) {
                        System.out.println("Not found\n");
                    } else{
                    System.out.print("New date ("+ s.salesDate() +"): "); String sd = sc.nextLine(); LocalDate d = sd.isBlank()? s.salesDate() : LocalDate.parse(sd.trim());
                    System.out.print("New DNI ("+(s.dniMember() ==null?"null": s.dniMember())+"): "); String dn = sc.nextLine(); String dni = dn.isBlank()? s.dniMember() : dn.trim();
                    SaleClass.update(new SaleClass.Sale(id, s.totalPrice(), d, dni));
                    System.out.println("Updated.\n");
                    }
                }
                case "4" -> { //Delete sale
                    System.out.print("Sale ID to delete: "); int id = Integer.parseInt(sc.nextLine());
                    SaleClass.delete(id);
                    System.out.println("Deleted.\n");
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option\n");
            }
        }
    }

    /**
     * Link-table (Items-Sales) sub-menu. Allows linking items to a sale,
     * updating quantities, removing links and listing items for a sale.
     */
    private static void itemSaleMenu(Scanner sc) throws SQLException {
        boolean back = false;
        while (!back) { //Menu
            System.out.println("--- Items-Sales ---");
            System.out.println("1. Add item to sale");
            System.out.println("2. Update quantity");
            System.out.println("3. Remove item from sale");
            System.out.println("4. List items for a sale");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> { //Add item to sale
                    int saleId = askInt(sc, "Sale ID: ");
                    int itemId = askInt(sc, "Item ID: ");
                    int qty = askInt(sc, "Quantity: ");
                    ItemSale.add(itemId, saleId, qty);
                    System.out.println("Linked.\n");
                }
                case "2" -> { //Update quantity
                    int saleId = askInt(sc, "Sale ID: ");
                    int itemId = askInt(sc, "Item ID: ");
                    int qty = askInt(sc, "New quantity: ");
                    ItemSale.update(itemId, saleId, qty);
                    System.out.println("Updated.\n");
                }
                case "3" -> { //Remove item from sale
                    int saleId = askInt(sc, "Sale ID: ");
                    int itemId = askInt(sc, "Item ID: ");
                    ItemSale.delete(itemId, saleId);
                    System.out.println("Removed.\n");
                }
                case "4" -> { //List items for a sale
                    int saleId = askInt(sc, "Sale ID: ");
                    ItemSale.listBySale(saleId).forEach(System.out::println);
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option\n");
            }
        }
    }


    //Returns the default value when the input string is null/blank, otherwise trims and returns the input.

    private static String emptyOr(String in, String def) {
        return in == null || in.isBlank() ? def : in.trim();
    }


     // Prompts and parses a required integer from the input
    private static int askInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(sc.nextLine());
    }
}