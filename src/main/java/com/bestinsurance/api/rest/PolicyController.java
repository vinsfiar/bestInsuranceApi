package com.bestinsurance.api.rest;

import com.bestinsurance.api.domain.Policy;
import com.bestinsurance.api.dto.PolicyCreation;
import com.bestinsurance.api.dto.PolicyUpdate;
import com.bestinsurance.api.dto.PolicyView;
import com.bestinsurance.api.dto.mappers.DTOMapper;
import com.bestinsurance.api.dto.mappers.PolicyViewMapper;
import com.bestinsurance.api.services.CrudService;
import com.bestinsurance.api.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
/**
 * Controller impelmenting the Policies crud API
 */
@RestController
@RequestMapping("/policies")
public class PolicyController extends AbstractSimpleIdCrudController<PolicyCreation, PolicyUpdate, PolicyView, Policy> {
    @Autowired
    private PolicyService policyService;
    @Override
    protected CrudService<Policy, UUID> getService() {
        return this.policyService;
    }

    @Override
    protected DTOMapper<PolicyCreation, Policy> getCreateDtoMapper() {
        return (dto) -> {
            Policy policy = new Policy();
            policy.setName(dto.getName());
            policy.setDescription(dto.getDescription());
            policy.setPrice(dto.getPrice());
            return policy;
        };
    }

    @Override
    protected DTOMapper<PolicyUpdate, Policy> getUpdateDtoMapper() {
        return (dto) -> {
            Policy policy = new Policy();
            policy.setName(dto.getName());
            policy.setDescription(dto.getDescription());
            policy.setPrice(dto.getPrice());
            return policy;
        };
    }

    @Override
    protected DTOMapper<Policy, PolicyView> getSearchDtoMapper() {
        return new PolicyViewMapper();
    }

}
