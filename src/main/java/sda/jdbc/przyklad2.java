/*
 * przyklad2
 * Przyklad pokazuje jak podlaczyc sie do bazy danych, wykonac zapytanie SELECT
 * wykozystujac interface Statement i wyswietla wynik na konsoli.
 * */

package sda.jdbc;

import java.sql.*;

public class przyklad2 {
    public static void main(String arg[]) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM autor;");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                System.out.println(id + " " + imie + " " + nazwisko);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}