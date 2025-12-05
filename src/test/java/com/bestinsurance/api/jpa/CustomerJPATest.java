package com.bestinsurance.api.jpa;

import com.bestinsurance.api.config.DomainConfig;
import com.bestinsurance.api.domain.Customer;
import com.bestinsurance.api.repos.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(DomainConfig.class)
public class CustomerJPATest {
    @Autowired
    private CustomerRepository customerRepository;
    @Test
    public void testCreate() {
        Customer customer = new Customer();
        customer.setName("TEST_NAME");
        customer.setSurname("TEST_SURNAME");
        customer.setEmail("TEST_EMAIL");
        Customer savedCustomer = customerRepository.save(customer);
        Optional<Customer> retrievedCustomer = customerRepository.findById(savedCustomer.getCustomerId());
        assertTrue(retrievedCustomer.isPresent());
        assertEquals(savedCustomer.getCustomerId(), retrievedCustomer.get().getCustomerId());
        assertEquals(savedCustomer.getSurname(), retrievedCustomer.get().getSurname());
        assertEquals(savedCustomer.getName(), retrievedCustomer.get().getName());
        assertEquals(savedCustomer.getEmail(), retrievedCustomer.get().getEmail());
        assertNotNull(retrievedCustomer.get().getCreated());
        assertNotNull(retrievedCustomer.get().getUpdated());
    }
}
