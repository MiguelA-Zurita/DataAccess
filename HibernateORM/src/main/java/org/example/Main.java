package org.example;

import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Session session; // Hibernate session

    public static void main(String[] args) {

        HibernateUtil.buildSessionFactory(); // Build session factory
        boolean exit = false;
        Scanner sc = new Scanner(System.in); // Scanner
        try {
            HibernateUtil.openSessionAndBindToThread(); // Open session and bind to thread
            session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get session from factory
            while (!exit) {
                System.out.println("----- Hibernate x MySQL CRUD -----"); // Menu
                System.out.println("1. Authors");
                System.out.println("2. Books");
                System.out.println("0. Exit");
                System.out.print("Choose option: ");
                String opt = sc.nextLine().trim();
                try {
                    switch (opt) {
                        case "1" -> authorMenu(sc); // Author menu
                        case "2" -> itemMenu(sc); // Item menu
                        case "0" -> exit = true;
                        default -> System.out.println("Invalid option\n");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            System.out.println("Bye!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread(); // Close session and unbind from thread
        }
        HibernateUtil.closeSessionFactory(); // Close session factory
    }

    public static void authorMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("--- Authors ---"); // Author menu
            System.out.println("1. Add");
            System.out.println("2. List");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> { // Add author
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("date of birth: ");
                    String dateBirth = sc.nextLine().trim();
                    Author author = new Author(name, dateBirth); // Create author
                    session.beginTransaction(); // begins the transaction
                    session.persist(author); // insert author into DB
                    session.getTransaction().commit(); // commits the transaction
                    System.out.println("Added");
                }
                case "2" -> {
                    List<Author> list = session.createQuery("FROM Author", Author.class).list(); // Get all authors
                    list.forEach(System.out::println); // Print authors
                }
                case "3" -> {
                    System.out.print("ID to update: ");
                    String id = sc.nextLine().trim();
                    Author author = session.find(Author.class, Integer.parseInt(id)); // Find author by ID
                    // Updates author if found, else reports failure
                    if (author == null) {
                        System.out.println("Not found");
                    } else{
                        System.out.print("New Name ("+ author.getName() +"): ");
                        author.setName(sc.nextLine().trim());
                        System.out.print("New date of birth ("+ author.getBirthDate() +"): ");
                        author.setBirthDate(sc.nextLine().trim());
                        System.out.println("Updated");
                    }
                }
                case "4" -> { // Delete author
                    System.out.print("ID to delete: "); String id = sc.nextLine().trim();
                    Author author = session.find(Author.class, Integer.parseInt(id)); // Find author by ID
                    session.beginTransaction(); // Begin transaction
                    session.remove(author); // Delete author
                    session.getTransaction().commit(); // Commit transaction
                    System.out.println("Deleted");
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }

    static void itemMenu(Scanner sc) {
        boolean back = false;
        // Loops book menu until user chooses back option
        while (!back) {
            System.out.println("--- Books ---");
            System.out.println("1. Add");
            System.out.println("2. List");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();
            // Handles book menu options; invalid input prints message
            switch (opt) {
                case "1" -> { //Add book
                    System.out.print("Title: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Year of publication: ");
                    int dateBirth = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Author ID: ");
                    int authorId = Integer.parseInt(sc.nextLine().trim());
                    Book book = new Book(name, dateBirth, session.find(Author.class, authorId)); // Create book
                    session.beginTransaction();
                    session.persist(book); //Insert Book into DB
                    session.getTransaction().commit();
                    System.out.println("Added");
                }
                case "2" -> { //List books
                    session.beginTransaction();
                    List<Book> list = session.createQuery("FROM Book", Book.class).list(); //Select all books
                    session.getTransaction().commit();
                    list.forEach(System.out::println); //Print books
                }
                case "3" -> { //Update book
                    System.out.print("ID to update: ");
                    String id = sc.nextLine().trim();
                    session.beginTransaction();
                    Book book = session.find(Book.class, Integer.parseInt(id)); //Find book by ID
                    session.getTransaction().commit();
                    // Updates book properties if book is found
                    if (book == null) {
                        System.out.println("Not found");
                    } else{ //Update the attributes of the book
                        System.out.print("New Name ("+ book.getTitle() +"): ");
                        book.setTitle(sc.nextLine().trim());
                        System.out.print("New year of publication ("+ book.getYear() +"): ");
                        book.setYear(Integer.parseInt(sc.nextLine().trim()));
                        System.out.println("Updated");
                    }
                }
                case "4" -> { //Delete book
                    System.out.print("ID to delete: "); String id = sc.nextLine().trim();
                    Book book = session.find(Book.class, Integer.parseInt(id)); //Find book by ID
                    session.beginTransaction();
                    session.remove(book); //Delete book
                    session.getTransaction().commit();
                    System.out.println("Deleted");
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }
}
