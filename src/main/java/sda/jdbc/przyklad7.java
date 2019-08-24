/*
 * przyklad7
 * Przyklad pokazuje jak nie robic parametryzacji. Jesli podamy w parametrze odpowiedni spreparowane dane,
 * to uzyskamy nieautoryzowany dostęp do danych. W takich sytuacjach dopuszczamy do sql injection-
 * wstrzykiwanie zlosliwego kodu.
 * */

package sda.jdbc;

import java.sql.*;

public class przyklad7 { //tutaj jest pokazane dlaczego nie robimy z plusikami + Konkatynacja parametrów)
    private final static String sqlSelect = "SELECT * FROM ksiazka WHERE id=";

    public static void main(String arg[]) {
        String parametr = "3"; // jak wstawimy jako parametr "3 OR 1=1" to mamy sqlInjection
//        String parametr = "3 OR 1=1"; przykład jak można wyciągnąć dane
        Connection connection = null;
        PreparedStatement preStmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            preStmt = connection.prepareStatement(sqlSelect + parametr);

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
