package com.demsasha;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String instanceName = "serverName\\instanseName";
        String databaseName = "test";
        String userName = "Usr";
        String pass = "12345";
        String connectionUrl = "jdbc:sqlserver://%1$s;databaseName=%2$s;user=%3$s;password=%4$s;";
        String connectionString = String.format(connectionUrl, instanceName, databaseName, userName, pass);
        String DBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(DBDriver);
            System.out.println(connectionString);
            Connection con = DriverManager.getConnection(connectionString);
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE people(" +
                    "id BIGINT IDENTITY(1,1) PRIMARY KEY," +
                    "name VARCHAR(20) NOT NULL," +
                    "cash DOUBLE NOT NULL," +
                    "is_worker BOOLEAN NOT NULL)");
            statement.execute("INSERT INT people (" +
                    "name," +
                    "cash," +
                    "is_worker)" +
                    "VALUES (" +
                    "'Alexander Dementev'" +
                    "19876.60" +
                    "true)");
            ResultSet result = statement.executeQuery("SELECT * FROM people");
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                double cash = result.getDouble("cash");
                boolean isWorker = result.getBoolean(4);
                System.out.println("id = " + id);
                System.out.println("name = " + name);
                System.out.println("cash = " + cash);
                if (isWorker) {
                    System.out.println("Worker");
                } else {
                    System.out.println("Client");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
