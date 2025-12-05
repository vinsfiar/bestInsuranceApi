package com.bestinsurance.api.services;

import com.bestinsurance.api.domain.Customer;
import com.bestinsurance.api.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 * Service class managing the Customers.-.
 */
@Service
public class CustomerService extends AbstractCrudService<Customer, UUID>{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected void updatePreSave(Customer fetched, Customer toSave) {
        toSave.setName(fetched.getName());
        toSave.setSurname(fetched.getSurname());
	toSave.setBirthDate(fetched.getBirthDate());
    }

    @Override
    protected CrudRepository<Customer, UUID> getRepository() {
        return this.customerRepository;
    }

}
