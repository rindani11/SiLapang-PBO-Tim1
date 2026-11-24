/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import payment.Pembayaran;
import java.time.LocalDate;

/**
 *
 * @author rindaaaa
 */
public class Reservasi {
    private String kode;
    private Penyewa penyewa;
    private Lapangan lapangan;
    private SlotWaktu slot;
    private StatusReservasi status;
    private int totalBiaya;

    public Reservasi(String kode, Penyewa penyewa, Lapangan lapangan, LocalDate tgl, int jamMulai, int durasi) { 
        
        this.kode = kode;
        this.penyewa = penyewa;
        this.lapangan = lapangan;
        this.slot = new SlotWaktu(tgl, jamMulai, durasi); // Komposisi: dibuat di dalam induk
        this.status = StatusReservasi.MENUNGGU_PEMBAYARAN;
        this.totalBiaya = lapangan.hitungBiaya(durasi, jamMulai);
    }
     public void bayar(Pembayaran metode) {
         if (this.status == StatusReservasi.TERKONFIRMASI) {
        throw new IllegalStateException("Reservasi ini sudah dibayar!");
        }
        metode.bayar(this.totalBiaya);
        this.status = StatusReservasi.TERKONFIRMASI;
        }

    public Lapangan getLapangan() { return lapangan; }
    public SlotWaktu getSlot() { return slot; }
    public StatusReservasi getStatus() { return status; }
    public int getTotalBiaya() { return totalBiaya; }

   
    
}
