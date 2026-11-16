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

        Penyewa penyewa = new Penyewa("U01", "Nafis");
        Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);

        Reservasi res = service.buatReservasi("RSV-01", penyewa, futsal, LocalDate.now(), 19, 2, new PembayaranQRIS());
        view.tampilkan("Reservasi sukses! Status: " + res.getStatus() + " | Total: Rp" + res.getTotalBiaya());
    }
}
