package io.github.salazar.ecommerce.repository;

import java.util.*;

public interface Repository<K, V> {

    List<V> findAll();

    Optional<V> findById(K id);

    V save(V entity);

    V update(V entity);

    void delete(K id);
}
