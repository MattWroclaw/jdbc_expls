package sda.jdbc;

import java.sql.*;

public class zadanie2 {
    public static void main(String arg[]) {
        System.out.println("Wyswietlamy liste uzytkownikow");
        zadanie2.pobieranieUzytkownikow();
        System.out.println("Dodajemy nowego uzytkownika");
        zadanie2.dodawanieUzytkownika();
        System.out.println("Wyswietlamy liste uzytkownikow");
        zadanie2.pobieranieUzytkownikow();
        System.out.println("Robimy update nazwiska");
        updateNazwiska();
        System.out.println("Wyswietlamy liste uzytkownikow");
        zadanie2.pobieranieUzytkownikow();
    }

    public static void pobieranieUzytkownikow() {
        String selectAllUser = "SELECT * FROM uzytkownik";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(selectAllUser);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                System.out.println("pobrano uzytkownika: " + id + " " + imie + " " + nazwisko);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dodawanieUzytkownika() {
        String insertUzytkownika = "INSERT INTO uzytkownik (`imie`, `nazwisko`) VALUES (?,?)";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(insertUzytkownika);) {
            preStmt.setString(1, "nowy");
            preStmt.setString(2, "nowe Nazwisko");
            Integer liczbaDodanychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer pobiezIdOstatniego() {
        String selectOstatniId = "select max(id) from uzytkownik";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";
        Integer id = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(selectOstatniId);
            resultSet.next();
            id = resultSet.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void updateNazwiska(){
        String updateNazwisko = "UPDATE uzytkownik " +
                "SET " +
                "`nazwisko` = ? " +
                "WHERE `id` = ?; ";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(updateNazwisko);) {
            preStmt.setString(1, "update nazwisko");
            preStmt.setInt(2, pobiezIdOstatniego());
            Integer liczbaDodanychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
