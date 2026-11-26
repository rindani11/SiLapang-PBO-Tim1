/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author rindaaaa
 */
public interface Repository<T, ID> {
    void simpan(T entitas);
    Optional<T> cari(ID id);
    List<T> ambilSemua();
    
}
