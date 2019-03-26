/*
 * przyklad5
 * Przyklad pokazuje jak podlaczyc sie do bazy danych, wykonac zapytanie UPDATE z parametrami
 * wykozystujac interface PreparedStatement i wyswietla wynik na konsoli.
 * Przyklad pokazuje wykozystanie metody excecuteUpdate().
 * */

package sda.jdbc;

import java.sql.*;

public class przyklad5 {
    private static String sqlUpdate = "UPDATE adres " +
            "SET `nrmieszkania` = ? " +
            "WHERE `miejscowosc` = ?";

    public static void main(String arg[]) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preStmt = connection.prepareStatement(sqlUpdate);
            preStmt.setString(1, "13");
            preStmt.setString(2, "Gdańsk");
            int countUpdateRecords = preStmt.executeUpdate();

            System.out.println("zaktualizowano " + countUpdateRecords + " rekordow");

        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException e) {
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
