/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.JadwalBentrokException;
import exception.PembayaranGagalException;
import java.time.LocalDate;
import java.util.List;
import model.Lapangan;
import model.Penyewa;
import model.Reservasi;
import model.SlotWaktu;
import payment.Pembayaran;
import repository.Repository;


/**
 *
 * @author rindaaaa
 */
public class ReservasiService {
    private final Repository<Reservasi, String> repo;

    public ReservasiService(Repository<Reservasi, String> repo) {
        this.repo = repo;
    }

    public Reservasi buatReservasi(String kode, Penyewa p, Lapangan l, LocalDate tgl, int jam, int durasi, Pembayaran metode)
            throws JadwalBentrokException, PembayaranGagalException {
        cekSlotBentrok(l, tgl, jam, durasi);
        Reservasi baru = new Reservasi(kode, p, l, tgl, jam, durasi);
        baru.bayar(metode);
        repo.simpan(baru);
        return baru;
    }

    private void cekSlotBentrok(Lapangan l, LocalDate tgl, int jam, int durasi) throws JadwalBentrokException {
        SlotWaktu calon = new SlotWaktu(tgl, jam, durasi);
        for (Reservasi r : repo.ambilSemua()) {
            if (r.getLapangan().getKode().equals(l.getKode()) && r.getSlot().bentrokDengan(calon)) {
                throw new JadwalBentrokException(l.getKode(), tgl, jam);
            }
        }
    }

    public List<Reservasi> lihatSemuaReservasi() {
        return repo.ambilSemua();
    }
}