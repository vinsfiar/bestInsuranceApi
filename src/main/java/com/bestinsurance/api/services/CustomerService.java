package com.bestinsurance.api.services;


import com.bestinsurance.api.domain.Customer;
import com.bestinsurance.api.repos.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CustomerService implements CrudService<Customer>{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }
    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        this.customerRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    @Override
    public Optional<Customer> getById(UUID id) {
        return this.customerRepository.findById(id);
    }
    @Override
    public Customer update(UUID id, Customer customer) {
        Optional<Customer> toUpdateOpt = this.customerRepository.findById(id);
        if (toUpdateOpt.isPresent()) {
            customer.setCustomerId(id);
            customer.setName(toUpdateOpt.get().getName());
            customer.setSurname(toUpdateOpt.get().getSurname());
            return this.customerRepository.save(customer);
        } else {
            logger.debug("Tried to update a not existing record");
            throw new NoSuchElementException("Entity not found in database");
        }
    }
    @Override
    public void delete(UUID id) {
        Optional<Customer> toDeleteOpt = this.customerRepository.findById(id);
        if (toDeleteOpt.isPresent()) {
            this.customerRepository.delete(toDeleteOpt.get());
        } else {
            logger.debug("Tried to delete a not existing record");
            throw new NoSuchElementException("Entity not found in database");
        }

    }
}
