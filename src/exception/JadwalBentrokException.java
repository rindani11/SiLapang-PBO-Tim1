/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import java.time.LocalDate;

/**
 *
 * @author rindaaaa
 */
public class JadwalBentrokException extends SiLapangException {
    private final String kodeLapangan;
    private final LocalDate tanggal;
    private final int jamMulai;

    public JadwalBentrokException(String kodeLapangan, LocalDate tanggal, int jamMulai) {
        super("Jadwal bentrok pada lapangan " + kodeLapangan + " tanggal " + tanggal + " jam " + jamMulai + ":00");
        this.kodeLapangan = kodeLapangan;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
    }

    public String getKodeLapangan() { return kodeLapangan; }
    public LocalDate getTanggal() { return tanggal; }
    public int getJamMulai() { return jamMulai; }
}
    

