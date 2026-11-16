/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rindaaaa
 */
public abstract class Lapangan {
    private String kode;
    private String nama;
    private int tarifPerJam;

    public Lapangan(String kode, String nama, int tarifPerJam) {
        this.kode = kode;
        this.nama = nama;
        this.tarifPerJam = tarifPerJam;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getTarifPerJam() { return tarifPerJam; }
    public abstract int hitungBiaya(int durasiJam, int jamMulai);
}
