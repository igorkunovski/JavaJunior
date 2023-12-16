package org.example;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database");

        Methods.createTableBook(connection);
        Methods.tableData(connection);
        Methods.readTableData(connection);

        Methods.findBookByAuthor(connection, "Пушкин");
        Methods.findBookByAuthor(connection, "Оруэл");
        Methods.findBookByAuthor(connection, "Спилберг");

        connection.close();
    }

}