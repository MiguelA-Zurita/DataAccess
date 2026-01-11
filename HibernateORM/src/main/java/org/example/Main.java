package org.example;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Author author = new Author("J.K. Rowling", "1965-07-31");
            session.persist(author);

            Book book1 = new Book("Harry Potter and the Philosopher's Stone", 1997, author.getName());
            Book book2 = new Book("Harry Potter and the Chamber of Secrets", 1998, author.getName());
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
}
