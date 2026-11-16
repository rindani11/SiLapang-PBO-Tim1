/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rindaaaa
 */
public class LapanganFutsal extends Lapangan {
    public LapanganFutsal(String kode, String nama, int tarifPerJam) {
        super(kode, nama, tarifPerJam);
    }

    @Override
    public int hitungBiaya(int durasiJam, int jamMulai) {
        int total = getTarifPerJam() * durasiJam;
        return (jamMulai >= 18) ? (int) (total * 1.2) : total; // Tambahan 20% malam hari
    }
}
