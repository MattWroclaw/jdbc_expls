/*
 * przyklad6
 * Przyklad pokazuje jak podlaczyc sie do bazy danych, wykozystac kilkukrotnie
 * PreparedStatement do dodania kilku rekordow do tabeli.
 * */

package sda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class przyklad6 {
    private final static String sqlInsert = "INSERT INTO uzytkownik" +
            "(`imie`,`nazwisko`)" +
            "VALUES (?,?)";

    public static void main(String arg[]) {
        String[] nazwiska = {"Nowak", "Polak", "Dudek", "Wielki", "Wilki"};
        Connection connection = null;
        PreparedStatement preStmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            preStmt = connection.prepareStatement(sqlInsert);
            connection.setAutoCommit(false); // to służy do transakcji, czyli albo wszystko się wykona
            // albo nic. Tutaj się to wyłącza, bo domyślnie jest ustawiony, że po każdym poleceniu
//            jest dorzucany commit

            for (int i = 0; i < 5; i++) {
                preStmt.setString(1, "Jan");
                preStmt.setString(2, nazwiska[i]);
                preStmt.execute();
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
