/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.time.LocalDate;
/**
 *
 * @author rindaaaa
 */
public class SlotWaktu {
    private LocalDate tanggal;
    private int jamMulai;
    private int durasiJam;

    public SlotWaktu(LocalDate tanggal, int jamMulai, int durasiJam) {
        
        if (durasiJam <= 0 || jamMulai < 6 || jamMulai > 23) {
        throw new IllegalArgumentException("Jam mulai atau durasi sewa tidak valid!");
        }
        
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.durasiJam = durasiJam;
    }

    public int getJamMulai() { return jamMulai; }
    public int getDurasiJam() { return durasiJam; }
    public int jamSelesai() { return jamMulai + durasiJam; }

    public boolean bentrokDengan(SlotWaktu lain) {
        if (!this.tanggal.equals(lain.tanggal)) return false;
        return this.jamMulai < lain.jamSelesai() && lain.jamMulai < this.jamSelesai();
    }
}
