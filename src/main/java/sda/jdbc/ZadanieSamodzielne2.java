package sda.jdbc;

import javax.print.DocFlavor;
import java.sql.*;

public class ZadanieSamodzielne2 {

    private final static String sqlInsert = "INSERT INTO uzytkownik" +
            "(`imie`,`nazwisko`)" +
            "VALUES (?,?)";

    private final static String sqlUpdate =  "UPDATE uzytkownik SET `nazwisko` = ? WHERE `id` = ?";
    private final static String sqlMaxId = "SELECT max(id) FROM uzytkownik;";

    public void pobierzWszystkich() {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection  = DriverManager.getConnection(url, user, password);
            statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM uzytkownik;");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                System.out.println("Pobrano dane: id: "+id+", imie:"+imie+", nazwisko:"+nazwisko);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                statement.close();
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
    public void dodawanieNowegoUzytkownika(){
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection  = DriverManager.getConnection(url, user, password);


            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, "Tomasz");
            preparedStatement.setString(2, "Mann");
            preparedStatement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                preparedStatement.close();
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

    public void zmianaNazwiska(){
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection  = DriverManager.getConnection(url, user, password);

            preparedStatement = connection.prepareStatement(sqlMaxId);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next(); // tutaj wchodzę do 1 wiersza selecta
            int maxId = resultSet.getInt(1);

            preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, "NoweZmienioneNazwisko");
            preparedStatement.setInt(2, maxId);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ZadanieSamodzielne2 zad = new ZadanieSamodzielne2();
//        zad.pobierzWszystkich();
//        System.out.println("tutaj bede dodawac uzytkownika");
//        zad.dodawanieNowegoUzytkownika();
//        System.out.println("wypisuje wszystkich");
//        zad.pobierzWszystkich();
        System.out.println("zmiana nazwiska");
        zad.zmianaNazwiska();
        zad.pobierzWszystkich();

    }
}
