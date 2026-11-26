/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import exception.PembayaranGagalException;
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
        if (kode == null || kode.isBlank() || penyewa == null || lapangan == null) {
            throw new IllegalArgumentException("Data reservasi tidak lengkap!");
        }
        this.kode = kode;
        this.penyewa = penyewa;
        this.lapangan = lapangan;
        this.slot = new SlotWaktu(tgl, jamMulai, durasi);
        this.status = StatusReservasi.MENUNGGU_PEMBAYARAN;
        this.totalBiaya = lapangan.hitungBiaya(durasi, jamMulai);
    }

    public String getKode() {
        return kode;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public Lapangan getLapangan() {
        return lapangan;
    }

    public SlotWaktu getSlot() {
        return slot;
    }

    public StatusReservasi getStatus() {
        return status;
    }

    public int getTotalBiaya() {
        return totalBiaya;
    }

    public void bayar(Pembayaran metode) throws PembayaranGagalException {
        if (this.status == StatusReservasi.TERKONFIRMASI) {
            throw new IllegalStateException("Reservasi ini sudah dibayar!");
        }
        if (this.status == StatusReservasi.DIBATALKAN) {
            throw new IllegalStateException("Reservasi yang dibatalkan tidak bisa dibayar!");
        }
        if (!metode.bayar(totalBiaya)) {
            this.status = StatusReservasi.DIBATALKAN;
            throw new PembayaranGagalException("Pembayaran ditolak atau saldo tidak mencukupi!", totalBiaya);
        }
        this.status = StatusReservasi.TERKONFIRMASI;
    }

    public void batalkan() {
        this.status = StatusReservasi.DIBATALKAN;
    }
}
    

