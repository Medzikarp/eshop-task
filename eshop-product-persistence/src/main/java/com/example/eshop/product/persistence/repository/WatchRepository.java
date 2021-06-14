package com.example.eshop.product.persistence.repository;

import com.example.eshop.product.persistence.entity.Watch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends CrudRepository<Watch, Long> {
}
