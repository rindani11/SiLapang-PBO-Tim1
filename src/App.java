/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rindaaaa
 */
import exception.SiLapangException;
import java.time.LocalDate;
import model.*;
import payment.*;
import repository.*;
import service.ReservasiService;
import ui.KonsolView;

public class App {
    public static void main(String[] args) {
        Repository<Reservasi, String> repo = new InMemoryRepository<>(Reservasi::getKode);
        ReservasiService service = new ReservasiService(repo);
        KonsolView view = new KonsolView();

        jalankanSkenarioSukses(service, view);
        jalankanSkenarioGalat(service, view);
    }

    private static void jalankanSkenarioSukses(ReservasiService service, KonsolView view) {
        try {
            Penyewa p1 = new Penyewa("U01", "Nafis");
            Penyewa p2 = new Penyewa("U02", "Rindani");

            Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
            Lapangan badminton = new LapanganBadminton("L02", "Badminton B", 60000);
            Lapangan basket = new LapanganBasket("L03", "Basket C", 120000);

            Reservasi r1 = service.buatReservasi("RSV-01", p1, futsal, LocalDate.now(), 19, 2, new PembayaranQRIS());
            Reservasi r2 = service.buatReservasi("RSV-02", p2, badminton, LocalDate.now(), 10, 2, new PembayaranTunai());
            Reservasi r3 = service.buatReservasi("RSV-03", p1, basket, LocalDate.now(), 14, 3, new PembayaranQRIS());

            view.tampilkan("Reservasi 1 (Futsal) | Total: Rp" + r1.getTotalBiaya());
            view.tampilkan("Reservasi 2 (Badminton) | Total: Rp" + r2.getTotalBiaya());
            view.tampilkan("Reservasi 3 (Basket) | Total: Rp" + r3.getTotalBiaya());
            view.tampilkan("Total reservasi tercatat: " + service.lihatSemuaReservasi().size());
        } catch (SiLapangException e) {
            view.tampilkan("Gagal tak terduga: " + e.getMessage());
        }
    }

    private static void jalankanSkenarioGalat(ReservasiService service, KonsolView view) {
        try {
            Penyewa p1 = new Penyewa("U01", "Nafis");
            Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
            service.buatReservasi("RSV-04", p1, futsal, LocalDate.now(), 20, 1, new PembayaranTunai());
        } catch (SiLapangException e) {
            view.tampilkan("[DITANGANI] " + e.getMessage());
        }
    }
}