/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.*;
import payment.Pembayaran;
import repository.ReservasiRepository;
import java.time.LocalDate;

/**
 *
 * @author rindaaaa
 */
public class ReservasiService {
    private final ReservasiRepository repo; // Bergantung pada interface (DIP)

    public ReservasiService(ReservasiRepository repo) {
        this.repo = repo;
    }

    public Reservasi buatReservasi(String kode, Penyewa p, Lapangan l, LocalDate tgl, int jam, int durasi, Pembayaran metode) {
        SlotWaktu calon = new SlotWaktu(tgl, jam, durasi);
        for (Reservasi r : repo.ambilSemua()) {
            if (r.getLapangan().getKode().equals(l.getKode()) && r.getSlot().bentrokDengan(calon)) {
                throw new IllegalStateException("Jadwal bentrok!");
            }
        }
        Reservasi baru = new Reservasi(kode, p, l, tgl, jam, durasi);
        baru.bayar(metode);
        repo.simpan(baru);
        return baru;
    }
}