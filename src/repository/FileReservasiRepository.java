/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.Reservasi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rindaaaa
 */
public class FileReservasiRepository implements ReservasiRepository {
    private List<Reservasi> data = new ArrayList<>();

    @Override
    public void simpan(Reservasi reservasi) { data.add(reservasi); }

    @Override
    public List<Reservasi> ambilSemua() { return data; }
}