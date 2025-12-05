package com.bestinsurance.api.controllers;

import com.bestinsurance.api.domain.Customer;
import com.bestinsurance.api.dto.CustomerCreation;
import com.bestinsurance.api.repos.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerCreation customerCreateDTO = new CustomerCreation();
        customerCreateDTO.setName("testName");
        customerCreateDTO.setSurname("testSurname");
        customerCreateDTO.setEmail("testEmail@email.com");
        customerCreateDTO.setAddress("123 Test Street, APT 4");
        customerCreateDTO.setPostalCode("12345-44");
        customerCreateDTO.setIdCity("45576d7c-8d84-4422-9440-19ef80fa16f3");
        customerCreateDTO.setIdCountry("d4153ed2-91e6-40da-a3c5-1de8a6d0119c");
        customerCreateDTO.setIdState("4b62177f-7eb0-448e-86c1-3e168e44cc29");


        MvcResult mvcResult = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(customerCreateDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andReturn();
        String id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        Optional<Customer> customer = customerRepository.findById(UUID.fromString(id));
        assertTrue(customer.isPresent());
        assertNotNull(customer.get().getAddress());
        assertNotNull(customer.get().getAddress().getAddressId());
        assertNotNull(customer.get().getAddress().getCountry());
        assertNotNull(customer.get().getAddress().getCountry().getCountryId());
        assertNotNull(customer.get().getAddress().getCountry().getName());
        assertNotNull(customer.get().getAddress().getState().getStateId());
        assertNotNull(customer.get().getAddress().getState().getName());
        assertNotNull(customer.get().getAddress().getCity().getCityId());
        assertNotNull(customer.get().getAddress().getCity().getName());
    }
}
