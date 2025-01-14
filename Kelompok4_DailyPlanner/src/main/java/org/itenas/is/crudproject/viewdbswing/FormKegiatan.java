/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itenas.is.crudproject.viewdbswing;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.itenas.is.crudproject.controller.ControllerKegiatan;
import org.itenas.is.crudproject.dbconfig.ConnectionManager;
import org.itenas.is.crudproject.model.Kegiatan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author user
 */
public class FormKegiatan extends javax.swing.JFrame {

    private Connection con;
    private Boolean hasil;
    private ControllerKegiatan conKegiatan;
    private DefaultTableModel model;
    private final FormAfterLogin formAfterLogin;

    public FormKegiatan(FormAfterLogin formAfterLogin) throws SQLException {
    conKegiatan = new ControllerKegiatan();
    ConnectionManager conMan = new ConnectionManager();
    con = conMan.logOn();

    this.formAfterLogin = formAfterLogin;
    initComponents();
    setLocationRelativeTo(null);
    
    
    model = new DefaultTableModel();
    tabelKegiatan.setModel(model);
    model.addColumn("Jam");
    model.addColumn("Kegiatan");
    model.addColumn("Prioritas");
    model.addColumn("Status");
    
    loadTableData();
}

    private void loadTableData() {
    ConnectionManager conMan = new ConnectionManager();
    Connection con = conMan.logOn();

    String query = "SELECT * FROM kegiatan";
    
    try (PreparedStatement pstmt = con.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) tabelKegiatan.getModel();
        model.setRowCount(0);
        
        if (model.getColumnCount() != 4) {
            model.setColumnIdentifiers(new String[]{"Jam", "Kegiatan", "Prioritas", "Status"});
        }

        while (rs.next()) {
            String jam = rs.getString("Jam");
            String aktivitas = rs.getString("Aktivitas");
            String prioritas = rs.getString("Prioritas");
            String status = rs.getString("Status");
            if (status == null || status.isEmpty()) {
                status = "Belum selesai";
            }

            model.addRow(new Object[]{jam, aktivitas, prioritas, status});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Gagal memuat data tabel: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    } finally {
        conMan.logOff(con);
    }
}

    public final void getData() throws SQLException {
    DefaultTableModel dtm = (DefaultTableModel) tabelKegiatan.getModel();
    dtm.setRowCount(0);
    
    String[] columnNames = {"Jam", "Kegiatan", "Prioritas", "Status"};
    dtm.setColumnIdentifiers(columnNames);
    
    List<Kegiatan> listKegiatan = conKegiatan.showKegiatan();
    String[] data = new String[4]; 
    for (Kegiatan newKegiatan : listKegiatan) {
        data[0] = newKegiatan.getJam();
        data[1] = newKegiatan.getAktivitas();
        data[2] = newKegiatan.getPrioritas();
        data[3] = newKegiatan.getStatus() != null ? newKegiatan.getStatus() : "Belum selesai"; 
        
        dtm.addRow(data);
    }
}

    private void clearData() {
        txtJam.setText("");
        txtKegiatan.setText("");
        comboPrioritas.setToolTipText("");
        txtJam.setEditable(true);
        txtKegiatan.setEditable(true);
        comboPrioritas.setEditable(true);
    }

    private void reloadTabel() {
        ControllerKegiatan controller = new ControllerKegiatan();
        List<Kegiatan> kegiatanList = controller.findAll(); 
        DefaultTableModel model = (DefaultTableModel) tabelKegiatan.getModel();

        model.setRowCount(0);

    }
    
    public void refreshTable() {
    ControllerKegiatan controller = new ControllerKegiatan();
    controller.updateTable(tabelKegiatan, null); // Tidak ada filter, tampilkan semua data
}
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKegiatan = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnSelesaikanTugas = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        txtJam = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKegiatan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboPrioritas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnSubmit1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("jLabel2");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 123, 198));
        jLabel3.setText("DAILY PLANNER");

        jPanel2.setBackground(new java.awt.Color(219, 246, 233));

        tabelKegiatan.setBackground(new java.awt.Color(242, 249, 255));
        tabelKegiatan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tabelKegiatan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelKegiatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKegiatanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKegiatan);

        jPanel5.setBackground(new java.awt.Color(219, 246, 233));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        btnPrint.setBackground(new java.awt.Color(204, 0, 204));
        btnPrint.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-print-24.png")); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnUpdate1.setBackground(new java.awt.Color(51, 153, 255));
        btnUpdate1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnUpdate1.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-pencil-16.png")); // NOI18N
        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        btnDelete1.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDelete1.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-delete-24.png")); // NOI18N
        btnDelete1.setText("Delete");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnSelesaikanTugas.setBackground(new java.awt.Color(0, 204, 0));
        btnSelesaikanTugas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSelesaikanTugas.setForeground(new java.awt.Color(255, 255, 255));
        btnSelesaikanTugas.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-done-24 (1).png")); // NOI18N
        btnSelesaikanTugas.setText("Selesaikan Tugas");
        btnSelesaikanTugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaikanTugasActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-back-24.png")); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(102, 102, 255));
        btnLogout.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-logout-24.png")); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        txtJam.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtJam.setForeground(new java.awt.Color(102, 123, 198));
        txtJam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        txtJam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJamActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Jam");

        txtKegiatan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtKegiatan.setForeground(new java.awt.Color(102, 123, 198));
        txtKegiatan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtKegiatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKegiatanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("Kegiatan");

        comboPrioritas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        comboPrioritas.setForeground(new java.awt.Color(102, 123, 198));
        comboPrioritas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prioritas", "Tinggi", "Sedang", "Rendah" }));
        comboPrioritas.setBorder(new javax.swing.border.MatteBorder(null));
        comboPrioritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPrioritasActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Pilih Prioritas");

        btnSubmit1.setBackground(new java.awt.Color(41, 95, 152));
        btnSubmit1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSubmit1.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-submit-progress-24 (1).png")); // NOI18N
        btnSubmit1.setText("Submit");
        btnSubmit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmit1ActionPerformed(evt);
            }
        });

        btnClear1.setBackground(new java.awt.Color(255, 153, 51));
        btnClear1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnClear1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-erase-24.png")); // NOI18N
        btnClear1.setText("Clear");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-search-16 (1).png")); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(comboPrioritas, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtJam, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(txtKegiatan))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSubmit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)))
                .addGap(390, 390, 390))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnBack)
                .addGap(123, 123, 123)
                .addComponent(btnPrint)
                .addGap(113, 113, 113)
                .addComponent(btnSelesaikanTugas)
                .addGap(85, 85, 85)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKegiatan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboPrioritas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubmit1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelesaikanTugas)
                            .addComponent(btnLogout))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\icons8-to-do-list-100.png")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(1038, 1038, 1038)
                .addComponent(jLabel4)
                .addGap(255, 255, 255)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String fileName = System.getProperty("user.home") + "/Downloads/DailyPlanner_" + timestamp + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        document.add(new Paragraph("============================================================="));
        document.add(new Paragraph("                                        DAILY PLANNER           ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        document.add(new Paragraph("============================================================="));
        document.add(new Paragraph("Dibuat pada: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date())));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(4); 
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 6, 3, 3}); 

        table.addCell(new PdfPCell(new Paragraph("Jam", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        table.addCell(new PdfPCell(new Paragraph("Kegiatan", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        table.addCell(new PdfPCell(new Paragraph("Prioritas", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        table.addCell(new PdfPCell(new Paragraph("Status", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));

        DefaultTableModel model = (DefaultTableModel) tabelKegiatan.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            String jam = model.getValueAt(row, 0) != null ? model.getValueAt(row, 0).toString() : "";
            String kegiatan = model.getValueAt(row, 1) != null ? model.getValueAt(row, 1).toString() : "";
            String prioritas = model.getValueAt(row, 2) != null ? model.getValueAt(row, 2).toString() : "";
            String status = model.getValueAt(row, 3) != null ? model.getValueAt(row, 3).toString() : "";

            table.addCell(jam);
            table.addCell(kegiatan);
            table.addCell(prioritas);
            table.addCell(status);
        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph("*** Terimakasih Telah Menggunakan Daily Planner ***"));

        document.close();

        JOptionPane.showMessageDialog(this,
                "File PDF berhasil dibuat di: " + fileName,
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal membuat file PDF: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJamActionPerformed

    private void txtKegiatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKegiatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKegiatanActionPerformed

    private void btnSubmit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmit1ActionPerformed
        ControllerKegiatan controller = new ControllerKegiatan();

        String jam = txtJam.getText();
        String aktivitas = txtKegiatan.getText();
        String prioritas = comboPrioritas.getSelectedItem().toString();

        if (jam.isEmpty() || aktivitas.isEmpty() || prioritas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Kegiatan kegiatan = new Kegiatan(jam, aktivitas, prioritas, "Belum selesai");

        try {
            boolean isSaved = controller.create(kegiatan);
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                txtJam.setText("");
                txtKegiatan.setText("");
                comboPrioritas.setSelectedIndex(0);

                loadTableData();

                if (formAfterLogin != null) {
                    formAfterLogin.loadStats();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSubmit1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
                                   
    ControllerKegiatan controller = new ControllerKegiatan();

    String aktivitas = txtKegiatan.getText();
    String jam = txtJam.getText();
    String prioritas = (String) comboPrioritas.getSelectedItem();

    if (aktivitas.isEmpty() || jam.isEmpty() || prioritas.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int selectedRow = tabelKegiatan.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan diupdate terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Ambil data lama dari tabel
    String aktivitasLama = (String) tabelKegiatan.getValueAt(selectedRow, 1);
    String statusLama = (String) tabelKegiatan.getValueAt(selectedRow, 3); // Ambil status dari tabel

    int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin memperbarui data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {

        Kegiatan kegiatan = new Kegiatan(jam, aktivitas, prioritas, statusLama);

        try {
            controller.update(aktivitasLama, kegiatan);
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

            txtJam.setText("");
            txtKegiatan.setText("");
            comboPrioritas.setSelectedIndex(0);

            loadTableData();

            tabelKegiatan.setRowSelectionInterval(selectedRow, selectedRow);

            if (formAfterLogin != null) {
                formAfterLogin.loadStats();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        ControllerKegiatan controller = new ControllerKegiatan();

        String aktivitas = txtKegiatan.getText().trim();

        if (aktivitas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nama aktivitas yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus aktivitas ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Kegiatan kegiatan = controller.findByAktivitas(aktivitas);

                if (kegiatan != null) {
                    boolean isDeleted = controller.delete(aktivitas);
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                        txtKegiatan.setText("");
                        loadTableData();

                        if (formAfterLogin != null) {
                            formAfterLogin.loadStats();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Aktivitas tidak ditemukan di database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        txtJam.setText("");
        txtKegiatan.setText("");
        comboPrioritas.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Form telah dikosongkan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

        loadTableData();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void tabelKegiatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKegiatanMouseClicked
        int selectedRow = tabelKegiatan.getSelectedRow();

        if (selectedRow != -1) {
            Object jam = tabelKegiatan.getValueAt(selectedRow, 0);        
            Object aktivitas = tabelKegiatan.getValueAt(selectedRow, 1); 
            Object prioritas = tabelKegiatan.getValueAt(selectedRow, 2); 

            txtJam.setText(jam != null ? jam.toString() : "");
            txtKegiatan.setText(aktivitas != null ? aktivitas.toString() : "");

            if (prioritas != null) {
                for (int i = 0; i < comboPrioritas.getItemCount(); i++) {
                    if (comboPrioritas.getItemAt(i).equals(prioritas.toString())) {
                        comboPrioritas.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }//GEN-LAST:event_tabelKegiatanMouseClicked

    private void comboPrioritasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPrioritasActionPerformed
        // TODO add your handling code here:
        String prioritasDipilih = comboPrioritas.getSelectedItem().toString();

        System.out.println("Prioritas dipilih: " + prioritasDipilih);

    }//GEN-LAST:event_comboPrioritasActionPerformed

    private void btnSelesaikanTugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaikanTugasActionPerformed
        int selectedRow = tabelKegiatan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih aktivitas yang ingin diselesaikan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String aktivitas = tabelKegiatan.getValueAt(selectedRow, 1).toString();
        ControllerKegiatan controller = new ControllerKegiatan();
        Kegiatan kegiatan = controller.findByAktivitas(aktivitas);

        if (kegiatan != null) {
            kegiatan.setStatus("Selesai");
            try {
                controller.update(aktivitas, kegiatan);
                JOptionPane.showMessageDialog(this, "Aktivitas telah diselesaikan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                tabelKegiatan.setValueAt("Selesai", selectedRow, 3); // Kolom ke-3 adalah kolom Status

                if (formAfterLogin != null) {
                    formAfterLogin.loadStats();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui status! Aktivitas tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSelesaikanTugasActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        FormAfterLogin formAfterLogin = new FormAfterLogin();
        formAfterLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       int confirm = JOptionPane.showConfirmDialog(this,
        "Apakah Anda yakin ingin keluar dan kembali ke halaman awal?",
        "Konfirmasi Logout",
        JOptionPane.YES_NO_OPTION);
        
    if (confirm == JOptionPane.YES_OPTION) {
        // Membuka form awal
        FormAwal formAwal = new FormAwal(); 
        formAwal.setVisible(true);
        
        // Menutup form saat ini
        this.dispose(); 
    }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String searchTerm = txtSearch.getText().toLowerCase().trim();

        if (searchTerm.isEmpty()) {
            loadTableData(); 
            return;
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tabelKegiatan.getModel();
            model.setRowCount(0); // Hapus semua baris sebelumnya

            // Query untuk mencari data berdasarkan kolom yang relevan
            String query = "SELECT * FROM kegiatan WHERE " +
            "(LOWER(COALESCE(status, 'belum selesai')) LIKE ? OR " +
            "LOWER(prioritas) LIKE ? OR " +
            "LOWER(aktivitas) LIKE ? OR " +
            "LOWER(jam) LIKE ?)";

            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                String searchPattern = "%" + searchTerm + "%";
                pstmt.setString(1, searchPattern);
                pstmt.setString(2, searchPattern);
                pstmt.setString(3, searchPattern);
                pstmt.setString(4, searchPattern);

                try (ResultSet rs = pstmt.executeQuery()) {
                    // Tambahkan data ke tabel
                    while (rs.next()) {
                        String jam = rs.getString("jam");
                        String aktivitas = rs.getString("aktivitas");
                        String prioritas = rs.getString("prioritas");
                        String status = rs.getString("status");
                        if (status == null || status.isEmpty()) {
                            status = "Belum selesai";
                        }
                        //memfilter status jika pencarian "Selesai" atau "Belum Selesai"
                        if (searchTerm.equals("selesai") && !status.equalsIgnoreCase("selesai")) {
                            continue;
                        } else if (searchTerm.equals("belum selesai") && !status.equalsIgnoreCase("belum selesai")) {
                            continue;
                        }

                        model.addRow(new Object[]{jam, aktivitas, prioritas, status});
                    }
                }
            }

            // Menampilkan pesan jika hasil pencarian tidak ada
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this,
                    "Tidak ada kegiatan yang cocok dengan pencarian: '" + searchTerm + "'",
                    "Hasil Pencarian",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            // Menangani kesalahan di database
            JOptionPane.showMessageDialog(this,
                "Error saat melakukan pencarian: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSelesaikanTugas;
    private javax.swing.JButton btnSubmit1;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JComboBox<String> comboPrioritas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelKegiatan;
    private javax.swing.JTextField txtJam;
    private javax.swing.JTextField txtKegiatan;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
