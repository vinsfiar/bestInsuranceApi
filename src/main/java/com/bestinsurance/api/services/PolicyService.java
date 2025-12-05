package com.bestinsurance.api.services;

import com.bestinsurance.api.domain.Policy;
import com.bestinsurance.api.repos.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 * Service class managing the Policies.-.
 */
@Service
public class PolicyService extends AbstractCrudService<Policy, UUID> {
    @Autowired
    private PolicyRepository repository;
    @Override
    protected CrudRepository<Policy, UUID> getRepository() {
        return this.repository;
    }
}
