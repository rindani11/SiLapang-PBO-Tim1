/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author rindaaaa
 */
public class PembayaranGagalException extends SiLapangException {
    private final int tagihan;

    public PembayaranGagalException(String pesan, int tagihan) {
        super(pesan);
        this.tagihan = tagihan;
    }

    public int getTagihan() { return tagihan; }
}
    

