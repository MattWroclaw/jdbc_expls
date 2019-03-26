/*
 * przyklad7
 * Przyklad pokazuje jak nie robic parametryzacji. Jesli podamy w parametrze odpowiedni spreparowane dane,
 * to uzyskamy nieautoryzowany dostęp do danych. W takich sytuacjach dopuszczamy do sql injection-
 * wstrzykiwanie zlosliwego kodu.
 * */

package sda.jdbc;

import java.sql.*;

public class przyklad7 {
    private static String sqlSelect = "SELECT * FROM ksiazka WHERE id=";

    public static void main(String arg[]) {
        String parametr = "3"; // jak wstawimy jako parametr "3 OR 1=1" to mamy sqlInjection
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preStmt = connection.prepareStatement(sqlSelect + parametr);

            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String tytul = resultSet.getString("tytul");
                System.out.println(id + " " + tytul);
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
