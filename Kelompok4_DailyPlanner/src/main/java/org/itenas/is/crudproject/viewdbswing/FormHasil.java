/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itenas.is.crudproject.viewdbswing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.itenas.is.crudproject.dbconfig.ConnectionManager;

/**
 *
 * @author HAFIZHAH
 */
public class FormHasil extends javax.swing.JFrame {
    private Connection con;
    private DefaultTableModel model;
    private FormKegiatan formKegiatan;
    
    
    public FormHasil(FormAfterLogin formAfterLogin) throws SQLException {
        initComponents();
        this.formKegiatan = new FormKegiatan(formAfterLogin);
        setLocationRelativeTo(null);
        setupTable();
        loadDataFromFormKegiatan(); 
    }

    public FormHasil() throws SQLException {
        this(new FormAfterLogin());
    }

    private void setupTable() {
        String[] columnNames = {"Jam", "Kegiatan", "Prioritas", "Status"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Make table read-only
            }
        };
        
        tabelPilihan.setModel(model);
        
        tabelPilihan.getColumnModel().getColumn(0).setPreferredWidth(100);  // Jam
        tabelPilihan.getColumnModel().getColumn(1).setPreferredWidth(250);  // Kegiatan
        tabelPilihan.getColumnModel().getColumn(2).setPreferredWidth(100);  // Prioritas
        tabelPilihan.getColumnModel().getColumn(3).setPreferredWidth(100);  // Status
        
        tabelPilihan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    void loadDataFromFormKegiatan() {
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            con = connectionManager.logOn();
            
            if (con == null) {
                throw new SQLException("Koneksi database gagal");
            }

            String query = "SELECT Jam, Aktivitas as Kegiatan, Prioritas, " +
                         "CASE WHEN Status IS NULL OR Status = '' THEN 'Belum selesai' " +
                         "ELSE Status END as Status " +
                         "FROM kegiatan ORDER BY Jam";
            
            try (PreparedStatement pstmt = con.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                
                model.setRowCount(0); // Clear existing rows

                while (rs.next()) {
                    Object[] row = {
                        rs.getString("Jam"),
                        rs.getString("Kegiatan"),
                        rs.getString("Prioritas"),
                        rs.getString("Status")
                    };
                    model.addRow(row); 
                }

                tabelPilihan.revalidate();
                tabelPilihan.repaint();

                if (model.getRowCount() > 0) {
                    System.out.println("Data loaded successfully: " + model.getRowCount() + " rows");
                } else {
                    System.out.println("No data found in the database");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error mengambil data: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public javax.swing.JTable getTabelPilihan() {
    return this.tabelPilihan; 
}
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        tabelHasil = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPilihan = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Jam", "Kegiatan", "Prioritas", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));

        tabelHasil.setBackground(new java.awt.Color(255, 255, 255));

        tabelPilihan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Jam", "Kegiatan", "Prioritas", "Status"
            }
        ));
        tabelPilihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPilihanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelPilihan);

        jLabel3.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 123, 198));
        jLabel3.setText("DAILY PLANNER");

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-to-do-list-100.png")); // NOI18N

        btnKembali.setBackground(new java.awt.Color(204, 204, 204));
        btnKembali.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(51, 51, 51));
        btnKembali.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-back-24.png")); // NOI18N
        btnKembali.setText("Back");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabelHasilLayout = new javax.swing.GroupLayout(tabelHasil);
        tabelHasil.setLayout(tabelHasilLayout);
        tabelHasilLayout.setHorizontalGroup(
            tabelHasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelHasilLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(156, 156, 156))
            .addGroup(tabelHasilLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(tabelHasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnKembali)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        tabelHasilLayout.setVerticalGroup(
            tabelHasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelHasilLayout.createSequentialGroup()
                .addGroup(tabelHasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabelHasilLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelHasilLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(37, 37, 37)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKembali)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabelHasil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelHasil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelPilihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPilihanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelPilihanMouseClicked

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
      try {
        
        FormAfterLogin formAfterLogin = new FormAfterLogin(); 

        // Menampilkan FormAfterLogin
        formAfterLogin.setVisible(true);
        this.dispose();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Terjadi kesalahan saat kembali ke FormAfterLogin: " + ex.getMessage(), 
            "Kesalahan", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnKembaliActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel tabelHasil;
    private javax.swing.JTable tabelPilihan;
    // End of variables declaration//GEN-END:variables
}
