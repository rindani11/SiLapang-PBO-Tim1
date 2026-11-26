/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author rindaaaa
 */
public class InMemoryRepository<T, ID> implements Repository<T, ID> {
    private final Map<ID, T> data = new LinkedHashMap<>();
    private final Function<T, ID> pengambilId;

    public InMemoryRepository(Function<T, ID> pengambilId) {
        this.pengambilId = pengambilId;
    }

    @Override
    public void simpan(T entitas) {
        data.put(pengambilId.apply(entitas), entitas);
    }

    @Override
    public Optional<T> cari(ID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<T> ambilSemua() {
        return new ArrayList<>(data.values());
    }
}
