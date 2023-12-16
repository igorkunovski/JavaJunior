package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Methods {

    public static void findBookByAuthor(Connection connection, String auth) throws SQLException {

        try( Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery("select id, name, author, issued, price from book where author = " + "'" + auth.toUpperCase() + "'" );

            int counter = 1;

            while (result.next()) {
                int id = result.getInt("id");
                int issued = result.getInt("issued");
                int price = result.getInt("price");
                String name = result.getNString("name");
                String author = result.getNString("author");

                System.out.println("Nr:" + counter++ + " " + " id = " + id + " name = " + name + " author= " + author
                        + " issued at (year)= " + issued + " price= " + price);
            }
        }

    }

    public static void readTableData(Connection connection) throws SQLException {

        try( Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery("select id, name, author, issued, price from book");

            int counter = 1;

            while (result.next()) {
                int id = result.getInt("id");
                int issued = result.getInt("issued");
                int price = result.getInt("price");
                String name = result.getNString("name");
                String author = result.getNString("author");

                System.out.println("Nr:" + counter++ + " " + " id = " + id + " name = " + name + " author= " + author
                        + " issued at (year)= " + issued + " price= " + price);
            }
        }
    }

    public static void tableData(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("""
                    
                    insert into book(id, name, author, issued, price)
                    values
                        (1, 'Война и мир', 'ТОЛСТОЙ', 1965, 1000),
                        (2, 'Человек в футляре', 'ЧЕХОВ', 1990, 700),
                        (3, 'Евгений Онегин', 'ПУШКИН', 2020, 3000),
                        (4, 'Руслан и Людмила', 'ПУШКИН', 2022, 3000),
                        (5, 'Три сестры', 'ЧЕХОВ', 2000, 900),
                        (6, 'Преступление и наказание', 'ДОСТОЕВСКИЙ', 2020, 1900),
                        (7, 'Мастер и Маргарита', 'БУЛГАКОВ', 2010, 1500),
                        (8, '1984', 'ОРУЭЛ', 1990, 1100),
                        (9, 'Скотный двор', 'ОРУЭЛ', 2000, 1100),
                        (10, 'Обломов', 'ГОНЧАРОВ', 2020, 1900)               
                    """);
        }
    }

    public static void createTableBook(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {

            statement.execute("""
                    create table book (
                     id bigint,
                     name varchar(255),
                     author varchar(255),
                     issued int,
                     price int
                    )
                    """);
        }
    }
}
