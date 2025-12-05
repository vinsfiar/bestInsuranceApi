package com.bestinsurance.api.rest;

import com.bestinsurance.api.domain.*;
import com.bestinsurance.api.dto.CustomerCreation;
import com.bestinsurance.api.dto.CustomerUpdate;
import com.bestinsurance.api.dto.CustomerView;
import com.bestinsurance.api.dto.mappers.CustomerViewMapper;
import com.bestinsurance.api.dto.mappers.DTOMapper;
import com.bestinsurance.api.services.CrudService;
import com.bestinsurance.api.services.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
/**
 * Controller impelmenting the Customers crud API
 */
@RestController
@RequestMapping("/customers")
public class CustomerController extends AbstractSimpleIdCrudController<CustomerCreation, CustomerUpdate, CustomerView, Customer> {
    @Autowired
    private CustomerService customerService;

    @Override
    protected CrudService<Customer, UUID> getService() {
        return this.customerService;
    }

    @Override
    protected DTOMapper<CustomerCreation, Customer> getCreateDtoMapper() {
        return (customerCreationDTO) -> {
            Customer customer = new Customer();
            customer.setName(customerCreationDTO.getName());
            customer.setSurname(customerCreationDTO.getSurname());
            customer.setEmail(customerCreationDTO.getEmail());
            if (!StringUtils.isBlank(customerCreationDTO.getAddress())) {
                Address address = new Address();
                address.setAddress(customerCreationDTO.getAddress());
                address.setPostalCode(customerCreationDTO.getPostalCode());
                Country country = new Country();
                country.setCountryId(UUID.fromString(customerCreationDTO.getIdCountry()));
                address.setCountry(country);
                if (!StringUtils.isBlank(customerCreationDTO.getIdState())) {
                    State state = new State();
                    state.setStateId(UUID.fromString(customerCreationDTO.getIdState()));
                    address.setState(state);
                }
                City city = new City();
                city.setCityId(UUID.fromString(customerCreationDTO.getIdCity()));
                address.setCity(city);

                customer.setAddress(address);
            }
            return customer;
        };
    }

    @Override
    protected DTOMapper<CustomerUpdate, Customer> getUpdateDtoMapper() {
        return (customerUpdateDTO) -> {
            Customer customer = new Customer();
            customer.setEmail(customerUpdateDTO.getEmail());
            if (!StringUtils.isBlank(customerUpdateDTO.getAddress())) {
                Address address = new Address();
                address.setAddress(customerUpdateDTO.getAddress());
                address.setPostalCode(customerUpdateDTO.getPostalCode());
                Country country = new Country();
                country.setCountryId(UUID.fromString(customerUpdateDTO.getIdCountry()));
                address.setCountry(country);
                if (!StringUtils.isBlank(customerUpdateDTO.getIdState())) {
                    State state = new State();
                    state.setStateId(UUID.fromString(customerUpdateDTO.getIdState()));
                    address.setState(state);
                }
                City city = new City();
                city.setCityId(UUID.fromString(customerUpdateDTO.getIdCity()));
                address.setCity(city);

                customer.setAddress(address);
            }
            return customer;
        };
    }

    @Override
    protected DTOMapper<Customer, CustomerView> getSearchDtoMapper() {
        return new CustomerViewMapper();
    }

}
