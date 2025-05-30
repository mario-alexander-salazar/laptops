package io.github.salazar.ecommerce.service.impl;

import io.github.salazar.ecommerce.model.Audit;
import io.github.salazar.ecommerce.repository.Repository;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.Service;

import java.util.List;
import java.util.Optional;

public class AuditService implements Service<Integer, Audit> {

    private final Repository<Integer, Audit> repository = new AuditRepository();


    @Override public List<Audit> findAll() {
        return repository.findAll();
    }

    @Override public Optional<Audit> findById(Integer id) {
        return repository.findById(id);
    }

    @Override public Audit save(Audit entity) {
        throw new UnsupportedOperationException();
    }

    @Override public Audit update(Audit entity) {
        throw new UnsupportedOperationException();
    }

    @Override public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
