package com.bike.retoBikes.Repository.crudRepository;

import com.bike.retoBikes.Model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
}
