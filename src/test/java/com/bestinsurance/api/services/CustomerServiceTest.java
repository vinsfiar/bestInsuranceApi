package com.bestinsurance.api.services;

import com.bestinsurance.api.config.DomainConfig;
import com.bestinsurance.api.domain.Customer;
import com.bestinsurance.api.jpa.AbstractCustomerInitializedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import({DomainConfig.class, CustomerServiceTest.TestConf.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest extends AbstractCustomerInitializedTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testUpdate(){
        List<Customer> all = customerService.findAll();
        //Let's take one of the customers to update it
        Customer customer = all.get(0);
        String oldEmail = customer.getEmail();
        Customer customerUpdatedFields = new Customer();
        customerUpdatedFields.setEmail("newEmail@email.com");
        Customer updated = customerService.update(customer.getCustomerId(), customerUpdatedFields);
        assertEquals(customer.getName(), updated.getName());
        assertEquals(customer.getSurname(), updated.getSurname());
        assertNotEquals(oldEmail, updated.getEmail());
    }

    static class TestConf {
        @Bean
        public CustomerService policyService() {
            return new CustomerService();
        }
    }
}
