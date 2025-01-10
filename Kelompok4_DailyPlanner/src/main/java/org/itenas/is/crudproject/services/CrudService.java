/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itenas.is.crudproject.services;
 import java.util.List;
import org.itenas.is.crudproject.model.Kegiatan;
/**
 *
 * @author user
 */

   

public interface CrudService<T> {
    // Membuat data baru
    public boolean create(final Kegiatan object);

    // Mencari satu data berdasarkan prioritas
    T findByPrioritas(String prioritas);

    // Mencari satu data berdasarkan jam
    T findByJam(String jam);

    // Mencari satu data berdasarkan aktivitas
    T findByAktivitas(String aktivitas);

    // Mendapatkan semua data
    List<T> findAll();

    // Mengupdate data berdasarkan aktivitas
    void update(String aktivitas, T object);

    // Menghapus data berdasarkan aktivitas
    boolean delete(String aktivitas);
}


