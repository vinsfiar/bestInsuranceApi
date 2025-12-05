package com.bestinsurance.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudService<DomainObj> {

    DomainObj create(DomainObj obj);

    List<DomainObj> findAll();

    Optional<DomainObj> getById(UUID id);

    DomainObj update(UUID id, DomainObj obj);

    void delete(UUID id);
}
