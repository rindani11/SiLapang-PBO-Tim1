/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.time.LocalDate;
import model.*;
import payment.*;
import repository.*;


/**
 *
 * @author rindaaaa
 */
public class ReservasiServiceTest {
    public static void main(String[] args) {
        ReservasiRepository repo = new FileReservasiRepository();
        ReservasiService service = new ReservasiService(repo);
        Penyewa p = new Penyewa("U99", "Penguji");
        Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        LocalDate tglUji = LocalDate.now().plusDays(5);

        // 1. Skenario Berhasil: Buat reservasi & cek daftar reservasi
        Reservasi r1 = service.buatReservasi("TEST-01", p, futsal, tglUji, 19, 2, new PembayaranQRIS());
        cek(r1.getStatus() == StatusReservasi.TERKONFIRMASI, "Status harus TERKONFIRMASI");
        cek(r1.getTotalBiaya() == 240000, "Total biaya harus 240000");
        cek(!service.lihatSemuaReservasi().isEmpty(), "Daftar reservasi tidak boleh kosong");

        // 2. Skenario Gagal: Jadwal bentrok
        boolean bentrokTerdeteksi = false;
        try {
            service.buatReservasi("TEST-02", p, futsal, tglUji, 20, 1, new PembayaranTunai());
        } catch (IllegalStateException e) {
            bentrokTerdeteksi = true;
        }
        cek(bentrokTerdeteksi, "Harus gagal saat jadwal bentrok");

        // 3. Skenario Gagal: Validasi tarif <= 0
        boolean validasiTarifJalan = false;
        try {
            new LapanganBadminton("L02", "Badminton B", 0);
        } catch (IllegalArgumentException e) {
            validasiTarifJalan = true;
        }
        cek(validasiTarifJalan, "Harus gagal jika tarif <= 0");

        System.out.println("Semua pengujian v3 LULUS!");
    }

    static void cek(boolean kondisi, String pesan) {
        if (!kondisi) throw new AssertionError(pesan);
    }
}

