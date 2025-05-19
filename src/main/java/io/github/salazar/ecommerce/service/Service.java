package io.github.salazar.ecommerce.service;

import java.util.List;
import java.util.Optional;

public interface Service<K, V> {
    List<V> findAll();
    Optional<V> findById(K id);
    V save(V entity);
    V update(V entity);
    void delete(K id);
}
