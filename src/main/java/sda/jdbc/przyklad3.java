/*
 * przyklad3
 * Przyklad pokazuje jak podlaczyc sie do bazy danych, wykonac zapytanie SELECT z parametrem
 * wykozystujac interface PreparedStatement i wyswietla wynik na konsoli.
 * Przyklad pokazuje wykozystanie metody excecuteQuery().
 * */

package sda.jdbc;

import java.sql.*;

public class przyklad3 {
    public static void main(String arg[]) {
        Connection connection = null;
        PreparedStatement preStmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            preStmt = connection.prepareStatement("SELECT * FROM ksiazka WHERE tytul like \"%\" ? \"%\";"); //parametr to ?
            preStmt.setString(1, "Wydanie"); //1 = pierwszy znak zapytania
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String tytul = resultSet.getString("tytul");
                System.out.println(id + " " + tytul);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preStmt.close();
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
