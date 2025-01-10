package org.itenas.is.crudproject.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private Connection con;
    private final String url = "jdbc:mysql://localhost:3306/tubesoop";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String username = "root";
    private final String password = "basdat2024";

    public Connection logOn() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName(driver);
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Koneksi ke database berhasil.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }
        return con;
    }

    // Metode untuk menutup koneksi ke database
    public void logOff(Connection con1) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Koneksi ke database berhasil ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }

    // Metode alternatif untuk mendapatkan koneksi
    public Connection getConnection() {
        if (con == null || !isConnected()) {
            logOn();
        }
        return con;
    }

    // Metode untuk memeriksa apakah koneksi masih aktif
    private boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
