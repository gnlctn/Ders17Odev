package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class localDBconnections {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gonulDB";
        String kullaniciAdi = "root";
        String sifre = "admin";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection= DriverManager.getConnection(url, kullaniciAdi, sifre);
            String sqlSorgusu = "CREATE TABLE Personeller (" +
                    "PersonelID INT NOT NULL AUTO_INCREMENT, " +
                    "Ad VARCHAR(50) NOT NULL, " +
                    "Soyad VARCHAR(50) NOT NULL, " +
                    "Telefon VARCHAR(15), " +
                    "PRIMARY KEY (PersonelID)" +
                    ");";
            preparedStatement = connection.prepareStatement(sqlSorgusu);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection= DriverManager.getConnection(url, kullaniciAdi, sifre);
            String sqlSorgusu ="INSERT INTO Personeller (Ad, Soyad, Telefon) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlSorgusu);
            preparedStatement.setString(1, "Gönül");
            preparedStatement.setString(2, "Çetin");
            preparedStatement.setString(3, "53974764735");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection= DriverManager.getConnection(url, kullaniciAdi, sifre);
            String sqlSorgusu = "DELETE FROM Personeller WHERE PersonelID=?";
            preparedStatement = connection.prepareStatement(sqlSorgusu);
            preparedStatement.setInt(1, 3);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection= DriverManager.getConnection(url, kullaniciAdi, sifre);
            String sqlSorgusu = "SELECT * FROM Personeller";
            preparedStatement = connection.prepareStatement(sqlSorgusu);
            rs=preparedStatement.executeQuery();
            while (rs.next()) {
                int personelID = rs.getInt("PersonelID");
                String ad = rs.getString("Ad");
                String soyad = rs.getString("Soyad");
                String telefon = rs.getString("Telefon");
                System.out.println("PersonelID: " + personelID +
                        ", Ad: " + ad +
                        ", Soyad: " + soyad +
                        ", Telefon: " + telefon);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}