/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rindaaaa
 */
public class PengelolaLapangan extends Pengguna {
    private List<Lapangan> daftarLapangan = new ArrayList<>();

    public PengelolaLapangan(String id, String nama) {
        super(id, nama);
    }

    // Agregasi: objek Lapangan dibuat di luar, lalu ditambahkan ke pengelola
    public void tambahLapangan(Lapangan lapangan) {
        daftarLapangan.add(lapangan);
    }

    public List<Lapangan> getDaftarLapangan() { return daftarLapangan; }

    @Override
    public String peran() { return "PENGELOLA"; }
}
