package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        try (Scanner sc = new Scanner(System.in)) {
            while (!exit) {
                showMenus();
                switch (sc.nextInt()) {
                    case 1:
                        customerMenu();
                        break;
                    case 2:
                        salesMenu();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void showMenus() {
        System.out.println("Welcome to the ObjectDB Demo");
        System.out.println("1. Manage Customers");
        System.out.println("2. Manage Items");
        System.out.println("3. Manage Sales");
        System.out.println("4. Manage ItemSales");
        System.out.println("5. Exit");
    }

    public static void customerMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Customer Menu");
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. List Customers");
            System.out.println("5. Back");
            try (Scanner sc = new Scanner(System.in); Database db = new Database()) {
                switch (sc.nextInt()) {
                    case 1:
                        System.out.print("Type the DNI of the customer:");
                        String dni = sc.next();
                        System.out.print("Type the name of the customer:");
                        String name = sc.next();
                        System.out.print("Type the first surname of the customer:");
                        String firstSurname = sc.next();
                        System.out.print("Type the second surname of the customer:");
                        String secondSurname = sc.next();
                        db.insert(new Customer(dni, name, firstSurname, secondSurname));
                        break;

                    case 2:
                        System.out.print("Type the DNI of the customer to delete:");
                        String dniToDelete = sc.next();
                        db.delete(new Customer(dniToDelete, "", "", ""));
                        break;

                    case 3:
                        System.out.print("Type the DNI of the customer to update:");
                        String dniToUpdate = sc.next();
                        System.out.print("Type the new name of the customer:");
                        String newName = sc.next();
                        System.out.print("Type the new first surname of the customer:");
                        String newFirstSurname = sc.next();
                        System.out.print("Type the new second surname of the customer:");
                        String newSecondSurname = sc.next();
                        Customer customerToUpdate = new Customer(dniToUpdate, newName, newFirstSurname, newSecondSurname);
                        db.update(customerToUpdate);
                        break;
                    case 4:
                        List<Customer> customerList = (List<Customer>) db.selectAll(Customer.class);
                        for (Customer customer : customerList) {
                            System.out.println(customer);
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }
        }
    }

    public static void salesMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Item Menu");
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. Update Item");
            System.out.println("4. List Items");
            System.out.println("5. Back");
            try (Scanner sc = new Scanner(System.in); Database db = new Database()) {
                switch (sc.nextInt()) {
                    case 1:
                        System.out.print("Type the total of the sales:");
                        double total = sc.nextDouble();
                        System.out.print("Type the date of the sales:");
                        String date = sc.next();
                        System.out.print("Type the DNI of the customer:");
                        String dni = sc.next();
                        db.insert(new Sales(total, date, dni));
                        break;

                    case 2:
                        System.out.print("Type the id of the customer to delete:");
                        int idToDelete = sc.nextInt();
                        db.delete(new Sales(idToDelete, 0.0, "", ""));
                        break;

                    case 3:
                        System.out.print("Type the Id of the sale to update:");
                        int idToUpdate = sc.nextInt();
                        System.out.print("Type the new total of the sales:");
                        double newTotal = sc.nextDouble();
                        System.out.print("Type the new date of the sales:");
                        String newDate = sc.next();
                        System.out.print("Type the new DNI of the customer:");
                        String newDni = sc.next();
                        Sales customerToUpdate = new Sales(idToUpdate, newTotal, newDate, newDni);
                        db.update(customerToUpdate);
                        break;
                    case 4:
                        List<Sales> salesList = (List<Sales>) db.selectAll(Sales.class);
                        for (Sales sale : salesList) {
                            System.out.println(sale);
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }
        }
    }
    public static void itemMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Item Menu");
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. Update Item");
            System.out.println("4. List Items");
            System.out.println("5. Back");
            try (Scanner sc = new Scanner(System.in); Database db = new Database()) {
                switch (sc.nextInt()) {
                    case 1:
                        System.out.print("Type the name of the item:");
                        String name = sc.next();
                        System.out.print("Type the price of the item:");
                        double price = sc.nextDouble();
                        System.out.print("Type the DNI of the customer:");
                        int stock = sc.nextInt();
                        db.insert(new Item(name, price, stock));
                        break;

                    case 2:
                        System.out.print("Type the id of the item to delete:");
                        int idToDelete = sc.nextInt();
                        db.delete(new Item(idToDelete, "", 0.0, 0));
                        break;

                    case 3:
                        System.out.print("Type the Id of the item to update:");
                        int idToUpdate = sc.nextInt();
                        System.out.print("Type the new name of the item:");
                        String newName = sc.next();
                        System.out.print("Type the new price of the item:");
                        double newPrice = sc.nextDouble();
                        System.out.print("Type the new stock of the item:");
                        int newStock = sc.nextInt();
                        Item customerToUpdate = new Item(idToUpdate, newName, newPrice, newStock);
                        db.update(customerToUpdate);
                        break;
                    case 4:
                        List<Item> itemsList = (List<Item>) db.selectAll(Item.class);
                        for (Item item : itemsList) {
                            System.out.println(item);
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }
        }
    }
    public static void itemSalesMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("ItemSales Menu");
            System.out.println("1. Add ItemSales");
            System.out.println("2. Delete ItemSales");
            System.out.println("3. Update ItemSales");
            System.out.println("4. List ItemSales");
            System.out.println("5. Back");
            try (Scanner sc = new Scanner(System.in); Database db = new Database()) {
                switch (sc.nextInt()) {
                    case 1:
                        System.out.print("Type the id of the item:");
                        int idItem = sc.nextInt();
                        System.out.print("Type the id of the sale:");
                        int idSale = sc.nextInt();
                        System.out.print("Type the quantity of the item:");
                        int quantity = sc.nextInt();
                        db.insert(new ItemSales(idItem, idSale, quantity));
                        break;

                    case 2:
                        System.out.print("Type the id of the item to delete:");
                        int idToDelete = sc.nextInt();
                        System.out.print("Type the id of the sale to delete:");
                        int idSaleToDelete = sc.nextInt();
                        db.delete(new ItemSales(idToDelete, idSaleToDelete, 0));
                        break;

                    case 3:
                        System.out.print("Type the Id of the item to update:");
                        int idToUpdate = sc.nextInt();
                        System.out.print("Type the Id of the Sale to update:");
                        int idSaleToUpdate = sc.nextInt();
                        System.out.print("Type the new quantity of the item:");
                        int newQuantity = sc.nextInt();
                        ItemSales customerToUpdate = new ItemSales(idToUpdate, idSaleToUpdate, newQuantity);
                        db.update(customerToUpdate);
                        break;
                    case 4:
                        List<ItemSales> itemsList = (List<ItemSales>) db.selectAll(ItemSales.class);
                        for (ItemSales item : itemsList) {
                            System.out.println(item);
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }
        }
    }
}
