/*
 * przyklad9
 * Przyklad pokazuje pobieranie danych z bazy danych, wrzucenie ich do listy obiektów i manipulacja na stworzonej liscie.
 * */

package sda.jdbc.przyklad9;

import java.sql.*;
import java.util.ArrayList;

public class przyklad9 {
    private final static String sqlSelect = "SELECT * FROM ksiazka ";

    public static void main(String arg[]) {
        ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>();
        Connection connection = null;
        Statement stmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Ksiazka ksiazkaDB = new Ksiazka();
                ksiazkaDB.setId(resultSet.getInt("id"));
                ksiazkaDB.setTytul(resultSet.getString("tytul"));
                ksiazki.add(ksiazkaDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dane o pobranych ksiazkach. Pobrano " + ksiazki.size() + " ksiazek.");
        System.out.println("Lista ksiazek w bazie danych: ");
        for (Ksiazka ksiazkaLista : ksiazki) {
            System.out.println(ksiazkaLista.getId() + " " + ksiazkaLista.getTytul());
        }
    }
}
// wrzucanie danych z bazy danych z tabelki na obiekt w Javie to nazywa się mapowanie