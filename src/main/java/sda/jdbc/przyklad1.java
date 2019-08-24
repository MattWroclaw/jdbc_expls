/*
 * przyklad1
 * Przyklad pokazuje jak podlaczyc sie do bazy danych, wykonac zapytanie SELECT
 * wykozystujac interface Statement i wyswietla wynik na konsoli.
 * */

package sda.jdbc;

import java.sql.*;

public class przyklad1 {
    public static void main(String arg[]) {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //od wersji 4 nie trzeba tego wskazywać
            String url = "jdbc:mysql://localhost:3306/ksiegarnia"; // albo nazwa bazy danych albo nazwa schematu
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT imie, nazwisko FROM uzytkownik;");
            while (resultSet.next()) {
                String imie = resultSet.getString("imie"); // można też zrobic (columnIndex: 1)  w SQL liczymy od 1 nie od 0
                String nazwisko = resultSet.getString("nazwisko");
                System.out.println("pobrano uzytkownika: " + imie + " " + nazwisko);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close(); // kolejność zamykania jest w dowrotnej kolejności jak kolejność otwierania
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
