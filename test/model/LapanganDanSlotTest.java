/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rindaaaa
 */
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class LapanganDanSlotTest {

    @Test
    public void hitungBiayaFutsalJamNormal() {
        Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        assertEquals(200000, futsal.hitungBiaya(2, 10));
    }

    @Test
    public void hitungBiayaFutsalJamMalam() {
        Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        assertEquals(240000, futsal.hitungBiaya(2, 19));
    }

    @Test
    public void hitungBiayaBadminton() {
        Lapangan badminton = new LapanganBadminton("L02", "Badminton B", 60000);
        assertEquals(120000, badminton.hitungBiaya(2, 10));
    }

    @Test
    public void hitungBiayaBasket() {
        Lapangan basket = new LapanganBasket("L03", "Basket C", 120000);
        assertEquals(324000, basket.hitungBiaya(3, 14));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validasiDurasiNegatif() {
        new SlotWaktu(LocalDate.of(2026, 11, 26), 10, 0);
    }

    @Test
    public void deteksiSlotBentrok() {
        LocalDate tgl = LocalDate.of(2026, 11, 26);
        SlotWaktu s1 = new SlotWaktu(tgl, 19, 2); // 19:00 - 21:00
        SlotWaktu s2 = new SlotWaktu(tgl, 20, 1); // 20:00 - 21:00
        assertTrue(s1.bentrokDengan(s2));
    }

    @Test
    public void deteksiSlotTidakBentrok() {
        LocalDate tgl = LocalDate.of(2026, 11, 26);
        SlotWaktu s1 = new SlotWaktu(tgl, 19, 2); // 19:00 - 21:00
        SlotWaktu s2 = new SlotWaktu(tgl, 21, 1); // 21:00 - 22:00
        assertFalse(s1.bentrokDengan(s2));
    }

    @Test
    public void testEqualsDanHashCodeSlotWaktu() {
        LocalDate tgl = LocalDate.of(2026, 11, 26);
        SlotWaktu s1 = new SlotWaktu(tgl, 19, 2);
        SlotWaktu s2 = new SlotWaktu(tgl, 19, 2);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }
}

