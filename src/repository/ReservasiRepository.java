/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.Reservasi;
import java.util.List;

/**
 *
 * @author rindaaaa
 */
public interface ReservasiRepository {
    void simpan(Reservasi reservasi);
    List<Reservasi> ambilSemua();
}
