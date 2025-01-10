/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.crudproject.model;

import javax.swing.JComboBox;

/**
 *
 * @author user
 */
public class Kegiatan {

    private String jam;
    private String aktivitas;
    private String prioritas;
    private String status;
    // Deklaprasi JComboBox

    public Kegiatan() {
    }

    public Kegiatan(String jam, String aktivitas, String prioritas, String status) {
        this.jam = jam;
        this.aktivitas = aktivitas;
        this.prioritas = prioritas;
        this.status = status;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getNamaKegiatan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
