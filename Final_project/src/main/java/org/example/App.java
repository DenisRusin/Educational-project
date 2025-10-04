package org.example;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        Flyway flyway = Flyway.configure().dataSource(url, user, password).load(); // Миграция
        var res = flyway.migrate();
        System.out.println(res);

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(url);
        ds.setUser(user);
        ds.setPassword(password);

        Connection conn = ds.getConnection();


        performingCrudOperations(conn);

    }

    private static void performingCrudOperations(Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);

            insertNewProductAndCustomer(connection);
            createOrderForCustomer(connection);
            readLastFiveOrders(connection);
            updatePriceAndQuantities(connection);
            deletingTestRecords(connection);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }


    private static void updatePriceAndQuantities(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET quantity = " +
                "(quantity - ?)  WHERE description = ?");

        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Шкаф");
        preparedStatement.executeUpdate();

        System.out.println("Количество товара обновлено");

        preparedStatement = connection.prepareStatement("UPDATE product SET cost = ? " +
                "  WHERE description = ?");

        preparedStatement.setDouble(1, 12500);
        preparedStatement.setString(2, "Телевизор");
        preparedStatement.executeUpdate();

        System.out.println("Цена товара обновлена");

    }

    private static void readLastFiveOrders(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.first_name,\n" +
                "\t   c.last_name,\n" +
                "\t   p.description,\n" +
                "\t   o.quantity,\n" +
                "\t   o.order_date\n" +
                "\tFROM orders o\n" +
                "\tINNER JOIN customer c ON o.customer_id = c.customer_id\n" +
                "\tINNER JOIN product p ON o.product_id = p.product_id\n" +
                "\tORDER BY o.order_date DESC\n" +
                "    LIMIT 5");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " +
                    resultSet.getString("last_name") + " " +
                    resultSet.getString("description") + " " +
                    resultSet.getInt("quantity") + " " +
                    resultSet.getDate("order_date"));
        }
    }

    private static void createOrderForCustomer(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORDERS " +
                "(PRODUCT_ID, CUSTOMER_ID, ORDER_DATE,  QUANTITY, STATUS)" +
                " VALUES (?, ?, ?, ?, ?)");

        preparedStatement.setInt(1, 4);
        preparedStatement.setInt(2, 7);
        preparedStatement.setDate(3, java.sql.Date.valueOf("2025-09-11"));
        preparedStatement.setInt(4, 2);
        preparedStatement.setInt(5, 1);
        preparedStatement.executeUpdate();

        System.out.println("Заказ создан");

    }

    private static void insertNewProductAndCustomer(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT " +
                "(DESCRIPTION, COST, QUANTITY,  CATEGORY)" +
                "VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, "Лыжи");
        preparedStatement.setDouble(2, 5000.00);
        preparedStatement.setInt(3, 5);
        preparedStatement.setString(4, "Спорт");
        preparedStatement.executeUpdate();

        System.out.println("Товар успешно создан");


        preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER " +
                "(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL) " +
                "VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, "Сергей");
        preparedStatement.setString(2, "Федорович");
        preparedStatement.setString(3, "+7673835648");
        preparedStatement.setString(4, "test@mail.com");

        preparedStatement.executeUpdate();

        System.out.println("Пользователь успешно создан");
    }

    private static void deletingTestRecords(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE product_id = ?");
        preparedStatement.setInt(1, 11);

        int deleteRows = preparedStatement.executeUpdate();
        System.out.println("Товар с product_id " + deleteRows + " удален");
    }

}