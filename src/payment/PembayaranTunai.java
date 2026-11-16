/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payment;

/**
 *
 * @author rindaaaa
 */
public class PembayaranTunai implements Pembayaran {
    @Override
    public boolean bayar(int jumlah) { return jumlah > 0; }  
}
