package org.example;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementException;

import java.util.Optional;

public class remoteDBconnectios {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8722876";
        String kullaniciAdi = "sql8722876";
        String sifre = "xxxxxx";

        Jdbi jdbi = Jdbi.create(url, kullaniciAdi, sifre);
        try (Handle handle = jdbi.open()) {
            handle.execute("CREATE TABLE Personeller (PersonelID INT NOT NULL AUTO_INCREMENT, Ad VARCHAR(50) NOT NULL, Soyad VARCHAR(50) NOT NULL, Telefon VARCHAR(15), PRIMARY KEY (PersonelID))");
            handle.execute("INSERT INTO Personeller (Ad,Soyad,Telefon) VALUES (?,?,?)", "Gamze","Çelik","05385294635");
            handle.execute("DELETE FROM Personeller WHERE PersonelID=?",2);

            String result = handle.createQuery("SELECT Ad FROM Personeller WHERE PersonelID = :PersonelID")
                    .bind("PersonelID", 1)
                    .mapTo(String.class)
                    .one();

            System.out.println("Result: " + result);

        } catch (StatementException e) {
            e.printStackTrace();
        }

    }
}
