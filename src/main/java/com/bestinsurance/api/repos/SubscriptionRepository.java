package com.bestinsurance.api.repos;

import com.bestinsurance.api.domain.Subscription;
import com.bestinsurance.api.domain.SubscriptionId;
import org.springframework.data.repository.CrudRepository;


public interface SubscriptionRepository extends CrudRepository<Subscription, SubscriptionId> {
}
