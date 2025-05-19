package io.github.salazar.ecommerce.service.impl;

import io.github.salazar.ecommerce.model.MaritalStatus;
import io.github.salazar.ecommerce.repository.impl.MaritalStatusRepository;
import io.github.salazar.ecommerce.service.Service;

import java.util.List;
import java.util.Optional;

public class MaritalStatusService implements Service<Integer, MaritalStatus> {

    private final MaritalStatusRepository repository = new MaritalStatusRepository();

    @Override
    public List<MaritalStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MaritalStatus> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public MaritalStatus save(MaritalStatus entity) {
        return repository.save(entity);
    }

    @Override
    public MaritalStatus update(MaritalStatus entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
