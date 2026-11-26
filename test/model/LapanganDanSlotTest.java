/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author rindaaaa
 */


public class LapanganDanSlotTest {

    @ParameterizedTest(name = "Futsal jam {0} durasi {1} -> Rp{2}")
    @CsvSource({
        "17, 2, 200000",
        "18, 2, 240000",
        "20, 1, 120000"
    })
    void hitungBiayaFutsal(int jamMulai, int durasi, int diharapkan) {
        Lapangan futsal = new LapanganFutsal("L01", "Futsal A", 100000);
        assertEquals(diharapkan, futsal.hitungBiaya(durasi, jamMulai));
    }

    @ParameterizedTest(name = "Basket durasi {0} jam -> Rp{1}")
    @CsvSource({
        "2, 200000",
        "3, 270000"
    })
    void hitungBiayaBasket(int durasi, int diharapkan) {
        Lapangan basket = new LapanganBasket("L03", "Basket C", 100000);
        assertEquals(diharapkan, basket.hitungBiaya(durasi, 10));
    }

    @Test
    @DisplayName("TC-06: Tarif 0 dan negatif harus ditolak")
    void validasiTarifTidakValid() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new LapanganBadminton("L02", "Bdm", 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> new LapanganBadminton("L02", "Bdm", -50000))
        );
    }

    @Test
    @DisplayName("TC-07: Jam operasional batas bawah (5) dan batas atas (24) ditolak")
    void validasiJamOperasionalBatas() {
        LocalDate besok = LocalDate.now().plusDays(1);
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new SlotWaktu(besok, 5, 1)),
            () -> assertThrows(IllegalArgumentException.class, () -> new SlotWaktu(besok, 24, 1)),
            () -> assertDoesNotThrow(() -> new SlotWaktu(besok, 6, 1))
        );
    }

    @Test
    @DisplayName("TC-08: HashSet tidak menduplikasi SlotWaktu yang sama")
    void ujiEqualsHashCodeSlotWaktu() {
        LocalDate tgl = LocalDate.now().plusDays(1);
        Set<SlotWaktu> setSlot = new HashSet<>();
        setSlot.add(new SlotWaktu(tgl, 10, 2));
        setSlot.add(new SlotWaktu(tgl, 10, 2));
        assertEquals(1, setSlot.size());
    }
}
