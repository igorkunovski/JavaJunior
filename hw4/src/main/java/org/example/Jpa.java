package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;


public class Jpa {
    public static void main(String[] args){

        // generating connection
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        // filling up table with data
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Book book1 = new Book("1984", "ORWELL", 1985, 10);
            Book book2 = new Book("Evgeny Onegin", "PUSHKIN", 2000, 5);
            Book book3 = new Book("War and Piece", "TOLSTOI", 1995, 15);
            Book book4 = new Book("Crime and punishment", "DOSTOEVSKI", 2010, 11);
            Book book5 = new Book("Master and Margarita", "BULGAKOV", 1986, 14);
            Book book6 = new Book("Ruslan and Lyudmila", "PUSHKIN", 2000, 8);


            System.out.println("Book1 id before persist: " + book1.getId());
            System.out.println("Book4 id before persist: " + book2.getId());

            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            session.persist(book4);
            session.persist(book5);
            session.persist(book6);

            System.out.println("Book1 id after persist: " + book1.getId());
            System.out.println("Book4 id after persist: " + book4.getId());

            session.getTransaction().commit();
        }

        // changing data in the table
        try(Session session = sessionFactory.openSession()){

            session.beginTransaction();

            Book book6 = session.get(Book.class, 6);
            System.out.println("Price before change: " + book6.getPrice());
            book6.setPrice(99);

            session.merge(book6);
            session.getTransaction().commit();
        }

        // reading data from table
        try(Session session = sessionFactory.openSession()){
            Book myBook1 = session.get(Book.class, 1);
            System.out.println(myBook1);

            Book myBook2 = session.get(Book.class, 2);
            System.out.println(myBook2);

            Book myBook6 = session.get(Book.class, 6);
            System.out.println("Price after change and load:" + myBook6.getPrice());

            List<Book> books = session.createQuery(
                    "select b from Book b where b.author = 'PUSHKIN' or b.author = 'TOLSTOI'", Book.class).getResultList();

            System.out.println(books);
        }
        sessionFactory.close();
    }

}

