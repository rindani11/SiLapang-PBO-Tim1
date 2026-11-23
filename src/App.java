/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rindaaaa
 */
import model.*;
import payment.*;
import repository.*;
import service.ReservasiService;
import ui.KonsolView;
import java.time.LocalDate;


public class App {
    public static void main(String[] args) {
        ReservasiRepository repo = new FileReservasiRepository();
        ReservasiService service = new ReservasiService(repo);
        KonsolView view = new KonsolView();

        Penyewa penyewa1 = new Penyewa("U01", "Nafis");
        Penyewa penyewa2 = new Penyewa("U02", "Rindani");

        Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        Lapangan badminton = new LapanganBadminton("L02", "Badminton B", 60000);
        Lapangan basket = new LapanganBasket("L03", "Basket C", 120000);

        LocalDate tanggalMain = LocalDate.now().plusDays(1);

        // Fitur 1: Membuat reservasi untuk berbagai jenis lapangan & metode bayar
        Reservasi r1 = service.buatReservasi("RSV-01", penyewa1, futsal, tanggalMain, 19, 2, new PembayaranQRIS());
        view.tampilkan("Reservasi 1 (" + futsal.getNama() + ") | Status: " + r1.getStatus() + " | Total: Rp" + r1.getTotalBiaya());

        Reservasi r2 = service.buatReservasi("RSV-02", penyewa2, badminton, tanggalMain, 16, 2, new PembayaranTunai());
        view.tampilkan("Reservasi 2 (" + badminton.getNama() + ") | Status: " + r2.getStatus() + " | Total: Rp" + r2.getTotalBiaya());

        Reservasi r3 = service.buatReservasi("RSV-03", penyewa1, basket, tanggalMain, 10, 3, new PembayaranQRIS());
        view.tampilkan("Reservasi 3 (" + basket.getNama() + ") | Status: " + r3.getStatus() + " | Total: Rp" + r3.getTotalBiaya());

        // Fitur 2: Menampilkan jumlah seluruh reservasi tersimpan
        view.tampilkan("Total reservasi tercatat: " + service.lihatSemuaReservasi().size() + " transaksi");
        
    }
}
