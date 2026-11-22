/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rindaaaa
 */
public class LapanganBasket  extends Lapangan {
    public LapanganBasket(String kode, String nama, int tarifPerJam){
        super(kode, nama, tarifPerJam);
    }
    
    @Override
    public int hitungBiaya(int durasiJam, int jamMulai) {
        int total = getTarifPerJam() * durasiJam;
        return (durasiJam >= 3)? (int) (total * 0.9): total;
    }
    
}
