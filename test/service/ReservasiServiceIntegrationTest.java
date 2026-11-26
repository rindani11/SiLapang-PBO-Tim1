/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.JadwalBentrokException;
import exception.PembayaranGagalException;
import java.time.LocalDate;
import model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import payment.*;
import repository.*;

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

    @Before
    public void setUp() {
        repo = new InMemoryRepository<>(Reservasi::getKode);
        service = new ReservasiService(repo);
        penyewa = new Penyewa("U01", "Nafis");
        futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        tglUji = LocalDate.of(2026, 11, 26);
    }

    @Test
    public void buatReservasiSukses() throws Exception {
        Reservasi res = service.buatReservasi("RSV-01", penyewa, futsal, tglUji, 19, 2, new PembayaranQRIS());
        assertEquals(StatusReservasi.TERKONFIRMASI, res.getStatus());
        assertEquals(1, service.lihatSemuaReservasi().size());
    }

    @Test
    public void buatReservasiBentrok() throws Exception {
        service.buatReservasi("RSV-01", penyewa, futsal, tglUji, 19, 2, new PembayaranQRIS());
        try {
            service.buatReservasi("RSV-02", penyewa, futsal, tglUji, 20, 1, new PembayaranTunai());
            fail("Seharusnya melempar JadwalBentrokException");
        } catch (JadwalBentrokException ex) {
            assertEquals("L01", ex.getKodeLapangan());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void cegahPembayaranGanda() throws Exception {
        Reservasi res = service.buatReservasi("RSV-01", penyewa, futsal, tglUji, 10, 1, new PembayaranTunai());
        res.bayar(new PembayaranQRIS());
    }

    @Test(expected = PembayaranGagalException.class)
    public void pembayaranGagalTidakTersimpan() throws Exception {
        Pembayaran metodeGagal = jumlah -> false;
        try {
            service.buatReservasi("RSV-99", penyewa, futsal, tglUji, 12, 1, metodeGagal);
        } finally {
            assertTrue(service.lihatSemuaReservasi().isEmpty());
        }
    }
}