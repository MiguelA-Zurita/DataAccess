package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean exit = false; // Boolean to exit the program
        try (Scanner sc = new Scanner(System.in)) {
            while (!exit) { //Loop to show the menu and execute the selected option
                showMenus();
                switch (sc.nextInt()) { //Switch to execute the selected option
                    case 1:
                        customerMenu(sc);
                        break;
                    case 2:
                        itemMenu(sc);
                        break;
                    case 3:
                        salesMenu(sc);
                        break;
                    case 4:
                        itemSalesMenu(sc);
                        break;
                    case 5:
                        exit = true; // Exit the program
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void showMenus() { //Function to show the menu
        System.out.println("Welcome to the ObjectDB Demo");
        System.out.println("1. Manage Customers");
        System.out.println("2. Manage Items");
        System.out.println("3. Manage Sales");
        System.out.println("4. Manage ItemSales");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public static void customerMenu(Scanner sc) { // Function to manage customers
        boolean exit = false;
        while (!exit) {
            System.out.println("Customer Menu"); //Show the menu
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. List Customers");
            System.out.println("5. Back");
            try (Database db = new Database()) { //Try with resources to close the database connection
                System.out.print("Choose an option: ");
                switch (sc.nextInt()) { //Switch to execute the selected option
                    case 1: //Insert the customer
                        System.out.print("Type the DNI of the customer: ");
                        String dni = sc.next();
                        System.out.print("Type the name of the customer: ");
                        String name = sc.next();
                        System.out.print("Type the first surname of the customer: ");
                        String firstSurname = sc.next();
                        System.out.print("Type the second surname of the customer: ");
                        String secondSurname = sc.next();
                        db.insert(new Customer(dni, name, firstSurname, secondSurname)); //Insert the customer in the database
                        break;

                    case 2: //Delete the customer
                        System.out.print("Type the DNI of the customer to delete: ");
                        String dniToDelete = sc.next();
                        db.delete(new Customer(dniToDelete, "", "", "")); //Delete the customer from the database
                        break;

                    case 3: //Update the customer
                        System.out.print("Type the DNI of the customer to update: ");
                        String dniToUpdate = sc.next();
                        System.out.print("Type the new name of the customer: ");
                        String newName = sc.next();
                        System.out.print("Type the new first surname of the customer: ");
                        String newFirstSurname = sc.next();
                        System.out.print("Type the new second surname of the customer: ");
                        String newSecondSurname = sc.next();
                        Customer customerToUpdate = new Customer(dniToUpdate, newName, newFirstSurname, newSecondSurname); // Create the customer object with the new data
                        db.update(customerToUpdate);
                        break;
                    case 4: // List the customers
                        List<Customer> customerList = (List<Customer>) db.selectAll(Customer.class); // Get all the customers from the database
                        for (Customer customer : customerList) {
                            System.out.println(customer); // Print the customers
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void salesMenu(Scanner sc) { // Function to manage sales
        boolean exit = false;
        while (!exit) {
            System.out.println("Item Menu");
            System.out.println("1. Add Sales");
            System.out.println("2. Delete Sales");
            System.out.println("3. Update Sales");
            System.out.println("4. List Sales");
            System.out.println("5. Back");
            try (Database db = new Database()) { // Try with resources to close the database connection
                System.out.print("Choose an option: ");
                switch (sc.nextInt()) {
                    case 1: // Insert the sale
                        System.out.print("Type the total of the sales: ");
                        double total = sc.nextDouble();
                        System.out.print("Type the date of the sales: ");
                        String date = sc.next();
                        System.out.print("Type the DNI of the customer: ");
                        String dni = sc.next();
                        db.insert(new Sales(total, date, dni)); // Insert the sale in the database
                        break;

                    case 2: // Delete the sale
                        System.out.print("Type the id of the customer to delete: ");
                        int idToDelete = sc.nextInt();
                        db.delete(new Sales(idToDelete, 0.0, "", "")); // Delete the sale from the database
                        break;

                    case 3: // Update the sale
                        System.out.print("Type the Id of the sale to update: ");
                        int idToUpdate = sc.nextInt();
                        System.out.print("Type the new total of the sales: ");
                        double newTotal = sc.nextDouble();
                        System.out.print("Type the new date of the sales: ");
                        String newDate = sc.next();
                        System.out.print("Type the new DNI of the customer: ");
                        String newDni = sc.next();
                        Sales customerToUpdate = new Sales(idToUpdate, newTotal, newDate, newDni); // Create the sale object with the new data
                        db.update(customerToUpdate); // Update the sale in the database
                        break;
                    case 4:
                        List<Sales> salesList = (List<Sales>) db.selectAll(Sales.class); // Get all the sales from the database
                        for (Sales sale : salesList) {
                            System.out.println(sale); // Print the sales
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void itemMenu(Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Item Menu");
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. Update Item");
            System.out.println("4. List Items");
            System.out.println("5. Back");
            try (Database db = new Database()) { // Try with resources to close the database connection
                System.out.print("Choose an option: ");
                switch (sc.nextInt()) {
                    case 1: // Insert the item
                        System.out.print("Type the name of the item: ");
                        String name = sc.next();
                        System.out.print("Type the price of the item: ");
                        double price = sc.nextDouble();
                        System.out.print("Type the stock of the item: ");
                        int stock = sc.nextInt();
                        db.insert(new Item(name, price, stock)); // Insert the item in the database
                        break;

                    case 2: // Delete the item
                        System.out.print("Type the id of the item to delete: ");
                        int idToDelete = sc.nextInt();
                        db.delete(new Item(idToDelete, "", 0.0, 0)); // Delete the item from the databaseº
                        break;

                    case 3: // Update the item
                        System.out.print("Type the Id of the item to update: ");
                        int idToUpdate = sc.nextInt();
                        System.out.print("Type the new name of the item: ");
                        String newName = sc.next();
                        System.out.print("Type the new price of the item: ");
                        double newPrice = sc.nextDouble();
                        System.out.print("Type the new stock of the item: ");
                        int newStock = sc.nextInt();
                        Item customerToUpdate = new Item(idToUpdate, newName, newPrice, newStock); // Create the item object with the new data
                        db.update(customerToUpdate);
                        break;
                    case 4:
                        List<Item> itemsList = (List<Item>) db.selectAll(Item.class); // Get all the items from the database
                        for (Item item : itemsList) {
                            System.out.println(item); // Print the items
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void itemSalesMenu(Scanner sc) {  // Function to manage item sales
        boolean exit = false;
        while (!exit) {
            System.out.println("ItemSales Menu");
            System.out.println("1. Add ItemSales");
            System.out.println("2. Delete ItemSales");
            System.out.println("3. Update ItemSales");
            System.out.println("4. List ItemSales");
            System.out.println("5. Back");
            try (Database db = new Database()) { // Try with resources to close the database connection
                System.out.print("Choose an option: ");
                switch (sc.nextInt()) {
                    case 1: // Insert the item sales
                        System.out.print("Type the id of the item: ");
                        int idItem = sc.nextInt();
                        System.out.print("Type the id of the sale: ");
                        int idSale = sc.nextInt();
                        System.out.print("Type the quantity of the item: ");
                        int quantity = sc.nextInt();
                        db.insert(new ItemSales(idItem, idSale, quantity)); // Insert the item sales in the database
                        break;

                    case 2: // Delete the item sales
                        System.out.print("Type the id of the item to delete: ");
                        int idToDelete = sc.nextInt();
                        System.out.print("Type the id of the sale to delete: ");
                        int idSaleToDelete = sc.nextInt();
                        db.delete(new ItemSales(idToDelete, idSaleToDelete, 0)); // Delete the item sales from the database
                        break;

                    case 3: // Update the item sales
                        System.out.print("Type the Id of the item to update: ");
                        int idToUpdate = sc.nextInt();
                        System.out.print("Type the Id of the Sale to update: ");
                        int idSaleToUpdate = sc.nextInt();
                        System.out.print("Type the new quantity of the item: ");
                        int newQuantity = sc.nextInt();
                        ItemSales customerToUpdate = new ItemSales(idToUpdate, idSaleToUpdate, newQuantity); // Create the item sales object with the new data
                        db.update(customerToUpdate);
                        break;
                    case 4:
                        List<ItemSales> itemsList = (List<ItemSales>) db.selectAll(ItemSales.class);  // Get all the item sales from the database
                        for (ItemSales item : itemsList) {
                            System.out.println(item); // Print the item sales
                        }
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
