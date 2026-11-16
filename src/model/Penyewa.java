/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rindaaaa
 */
public class Penyewa extends Pengguna {
    public Penyewa(String id, String nama) {
        super(id, nama);
    }

    @Override
    public String peran() { return "PENYEWA"; }
}
    

