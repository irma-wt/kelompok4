package org.itenas.is.crudproject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.itenas.is.crudproject.dbconfig.ConnectionManager;
import org.itenas.is.crudproject.model.Kegiatan;
import org.itenas.is.crudproject.services.CrudService;

public class ControllerKegiatan implements CrudService<Kegiatan> {

    ConnectionManager conMan = new ConnectionManager();
    Connection con = conMan.logOn(); // Pastikan koneksi ke database 'kegiatan' berhasil

    @Override
    public boolean create(Kegiatan object) {
        String query = "INSERT INTO kegiatan (Jam, Aktivitas, Prioritas, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, object.getJam());
            pstmt.setString(2, object.getAktivitas());
            pstmt.setString(3, object.getPrioritas());
            pstmt.setString(4, "Belum selesai"); // Status default

            // Eksekusi query
            int rowsAffected = pstmt.executeUpdate();

            // Jika ada baris yang terpengaruh, data berhasil disimpan
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Error saat menyimpan data: " + ex.getMessage());
        }
        // Jika terjadi error, kembalikan false
        return false;
    }

    @Override
    public Kegiatan findByPrioritas(String prioritas) {
        String query = "SELECT * FROM kegiatan WHERE Prioritas = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, prioritas);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Kegiatan(rs.getString("Jam"), rs.getString("Aktivitas"), rs.getString("Prioritas"), rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Kegiatan findByJam(String jam) {
        String query = "SELECT * FROM kegiatan WHERE Jam = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, jam);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Kegiatan(rs.getString("Jam"), rs.getString("Aktivitas"), rs.getString("Prioritas"), rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Kegiatan findByAktivitas(String aktivitas) {
        String query = "SELECT * FROM kegiatan WHERE Aktivitas = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, aktivitas);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Kegiatan(rs.getString("Jam"), rs.getString("Aktivitas"), rs.getString("Prioritas"), rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Kegiatan> findAll() {
        List<Kegiatan> kegiatanList = new ArrayList<>();
        String query = "SELECT * FROM kegiatan";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                kegiatanList.add(new Kegiatan(rs.getString("Jam"), rs.getString("Aktivitas"), rs.getString("Prioritas"), rs.getString("Status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kegiatanList;
    }

    @Override
    public void update(String aktivitas, Kegiatan object) {
         String query = "UPDATE kegiatan SET Jam = ?, Aktivitas = ?, Prioritas = ?, Status = ? WHERE Aktivitas = ?";

    try (PreparedStatement pstmt = con.prepareStatement(query)) {

        // Cek apakah objek Kegiatan berisi nilai yang valid
        if (object.getJam() == null || object.getJam().isEmpty() || 
            object.getAktivitas() == null || object.getAktivitas().isEmpty() || 
            object.getPrioritas() == null || object.getPrioritas().isEmpty() || 
            object.getStatus() == null || object.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Semua field harus diisi.");
        }

        // Set parameter untuk prepared statement
        pstmt.setString(1, object.getJam());          // Jam
        pstmt.setString(2, object.getAktivitas());    // Aktivitas
        pstmt.setString(3, object.getPrioritas());    // Prioritas
        pstmt.setString(4, object.getStatus());       // Status
        pstmt.setString(5, aktivitas);                // Aktivitas lama untuk WHERE clause

        // Menjalankan query update
        int rowsUpdated = pstmt.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("Data berhasil diperbarui.");
        } else {
            System.out.println("Tidak ada data yang diperbarui. Mungkin aktivitas tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.err.println("Error saat memperbarui data: " + e.getMessage());
        e.printStackTrace();  // Agar lebih mudah untuk debugging
    } catch (IllegalArgumentException e) {
        System.err.println("Argument tidak valid: " + e.getMessage());
    }
    }

    @Override
    public boolean delete(String aktivitas) {
        String query = "DELETE FROM kegiatan WHERE Aktivitas = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, aktivitas);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data berhasil dihapus.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data: " + e.getMessage());
        }
        return false;
    }

    public List<Kegiatan> showKegiatan() {
        List<Kegiatan> listKegiatan = new ArrayList<>();
        String query = "SELECT * FROM kegiatan";
        try (PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Kegiatan kegiatan = new Kegiatan(
                        rs.getString("Jam"),
                        rs.getString("Aktivitas"),
                        rs.getString("Prioritas"),
                        rs.getString("Status")
                );
                listKegiatan.add(kegiatan);
            }
        } catch (SQLException ex) {
            System.err.println("Error saat mengambil data: " + ex.getMessage());
        }
        return listKegiatan;
    }

    public List<Kegiatan> getAllKegiatan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Mendapatkan semua tugas
public List<Kegiatan> getAllTasks() {
    return findAll();
}

// Mendapatkan tugas dengan status 'Selesai'
public List<Kegiatan> getCompletedTasks() {
    List<Kegiatan> completedTasks = new ArrayList<>();
    String query = "SELECT * FROM kegiatan WHERE Status = 'Selesai'";
    try (PreparedStatement pstmt = con.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            completedTasks.add(new Kegiatan(
                    rs.getString("Jam"),
                    rs.getString("Aktivitas"),
                    rs.getString("Prioritas"),
                    rs.getString("Status")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil data tugas selesai: " + e.getMessage());
    }
    return completedTasks;
}

// Mendapatkan tugas dengan prioritas 'Tinggi'
public List<Kegiatan> getHighPriorityTasks() {
    List<Kegiatan> highPriorityTasks = new ArrayList<>();
    String query = "SELECT * FROM kegiatan WHERE Prioritas = 'Tinggi'";
    try (PreparedStatement pstmt = con.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            highPriorityTasks.add(new Kegiatan(
                    rs.getString("Jam"),
                    rs.getString("Aktivitas"),
                    rs.getString("Prioritas"),
                    rs.getString("Status")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil data prioritas tinggi: " + e.getMessage());
    }
    return highPriorityTasks;
}
public void updateTable(javax.swing.JTable tabelKegiatan, String filterStatus) {
    // Validasi jika tabel tidak valid
    if (tabelKegiatan == null || tabelKegiatan.getModel() == null) {
        System.out.println("Tabel atau model tabel tidak valid.");
        return;
    }

    // Inisialisasi koneksi
    ConnectionManager conMan = new ConnectionManager();
    Connection con = conMan.logOn();
    DefaultTableModel model = (DefaultTableModel) tabelKegiatan.getModel();
    model.setRowCount(0); // Hapus data lama dari tabel

    // Buat query berdasarkan filter
    String query;
    if (filterStatus == null) {
        query = "SELECT * FROM kegiatan"; // Semua data tanpa filter
    } else {
        query = "SELECT * FROM kegiatan WHERE prioritas = ? OR status = ?"; // Filter prioritas atau status
    }

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        // Set parameter filter jika ada
        if (filterStatus != null) {
            stmt.setString(1, filterStatus);
            stmt.setString(2, filterStatus);
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // Ambil data dari hasil query
            Object[] row = {
                rs.getString("jam"),         // Kolom jam
                rs.getString("aktivitas"),  // Kolom aktivitas
                rs.getString("prioritas"),  // Kolom prioritas
                rs.getString("status")      // Kolom status
            };
            model.addRow(row); // Tambahkan data ke model tabel
        }
    } catch (SQLException e) {
        // Tampilkan error jika terjadi
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memuat data: " + e.getMessage());
    } finally {
        // Tutup koneksi untuk mencegah kebocoran
        conMan.logOff(con);
    }
}

    
}
