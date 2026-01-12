package org.example;

import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Session session;

    public static void main(String[] args) {

        HibernateUtil.buildSessionFactory();
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            while (!exit) {
                System.out.println("----- Hibernate x MySQL CRUD -----");
                System.out.println("1. Authors");
                System.out.println("2. Books");
                System.out.println("0. Exit");
                System.out.print("Choose option: ");
                String opt = sc.nextLine().trim();
                try {
                    switch (opt) {
                        case "1" -> authorMenu(sc);
                        case "2" -> itemMenu(sc);
                        case "0" -> exit = true;
                        default -> System.out.println("Invalid option\n");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            System.out.println("Bye!");
            Author author = new Author("J.K. Rowling", "1965-07-31");
            session.persist(author);

            Book book1 = new Book("Harry Potter and the Philosopher's Stone", 1997, author);
            Book book2 = new Book("Harry Potter and the Chamber of Secrets", 1998, author);
            session.persist(book1);
            session.persist(book2);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        HibernateUtil.closeSessionFactory();




    }

    public static void authorMenu(Scanner sc) {
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
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("date of birth: ");
                    String dateBirth = sc.nextLine().trim();
                    Author author = new Author(name, dateBirth);
                    session.persist(author);
                    System.out.println("Added");
                }
                case "2" -> {
                    List<Author> list = session.createQuery("FROM Author", Author.class).list();
                    list.forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("ID to update: ");
                    String id = sc.nextLine().trim();
                    Author author = session.find(Author.class, Integer.parseInt(id));
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
                case "4" -> {
                    System.out.print("DNI to delete: "); String dni = sc.nextLine().trim();
                    //TODO: delete author
                    System.out.println("Deleted");
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }

    static void itemMenu(Scanner sc) {

    }
}
