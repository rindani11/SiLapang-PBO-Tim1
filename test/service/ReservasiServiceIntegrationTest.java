/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import exception.JadwalBentrokException;
import exception.PembayaranGagalException;
import java.time.LocalDate;
import model.*;
import payment.*;
import repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rindaaaa
 */
public class ReservasiServiceIntegrationTest {
    private Repository<Reservasi, String> repo;
    private ReservasiService service;
    private Penyewa penyewa;
    private Lapangan futsal;
    private LocalDate tglUji;

    @BeforeEach
    void siapkan() {
        repo = new InMemoryRepository<>(Reservasi::getKode);
        service = new ReservasiService(repo);
        penyewa = new Penyewa("U01", "Nafis");
        futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        tglUji = LocalDate.now().plusDays(2);
    }

    @Test
    @DisplayName("TC-09 [Integrasi]: Buat reservasi sukses dan tersimpan")
    void buatReservasiSukses() throws Exception {
        Reservasi res = service.buatReservasi("RSV-01", penyewa, futsal, tglUji, 19, 2, new PembayaranQRIS());
        assertAll(
            () -> assertEquals(StatusReservasi.TERKONFIRMASI, res.getStatus()),
            () -> assertEquals(240000, res.getTotalBiaya()),
            () -> assertEquals(1, service.lihatSemuaReservasi().size())
        );
    }

    @Test
    @DisplayName("TC-10 [Integrasi]: Jadwal bentrok melempar JadwalBentrokException")
    void buatReservasiBentrok() throws Exception {
        service.buatReservasi("RSV-01", penyewa, futsal, tglUji, 19, 2, new PembayaranQRIS());
        JadwalBentrokException ex = assertThrows(JadwalBentrokException.class, () ->
            service.buatReservasi("RSV-02", penyewa, futsal, tglUji, 20, 1, new PembayaranTunai())
        );
        assertEquals("L01", ex.getKodeLapangan());
    }

    @Test
    @DisplayName("TC-11 [Integrasi & Regresi]: Pembayaran ganda ditolak")
    void cegahPembayaranGanda() throws Exception {
        Reservasi res = service.buatReservasi("RSV-01", penyewa, futsal, tglUji, 10, 1, new PembayaranTunai());
        assertThrows(IllegalStateException.class, () -> res.bayar(new PembayaranQRIS()));
    }

    @Test
    @DisplayName("TC-12 [Integrasi]: Pembayaran gagal tidak menyimpan reservasi")
    void pembayaranGagalTidakTersimpan() {
        Pembayaran metodeGagal = jumlah -> false;
        assertThrows(PembayaranGagalException.class, () ->
            service.buatReservasi("RSV-99", penyewa, futsal, tglUji, 12, 1, metodeGagal)
        );
        assertTrue(service.lihatSemuaReservasi().isEmpty());
    }
}
