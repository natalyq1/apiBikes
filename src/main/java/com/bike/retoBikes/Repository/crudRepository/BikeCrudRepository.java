package com.bike.retoBikes.Repository.crudRepository;

import com.bike.retoBikes.Model.Bike;
import org.springframework.data.repository.CrudRepository;

public interface BikeCrudRepository extends CrudRepository<Bike, Integer> {
}
