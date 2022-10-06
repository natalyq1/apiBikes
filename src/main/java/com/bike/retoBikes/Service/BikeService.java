package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.Bike;
import com.bike.retoBikes.Repository.BikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BikeService {
    @Autowired
    private BikeRepo bikeRepo;

    public List<Bike> getAll() {
        return bikeRepo.getAll();
    }

    public Optional<Bike> getBike(int id){
        return bikeRepo.getBike(id);
    }

    public Bike save(Bike bike) {
        if (bike.getId() == null) {
            return bikeRepo.save(bike);
        } else {
            Optional<Bike> e = bikeRepo.getBike(bike.getId());
            if (e.isPresent()) {
                return bike;
            } else {
                return bikeRepo.save(bike);
            }
        }
    }

    public Bike update(Bike bike) {
        if (bike.getId() != null) {
            Optional<Bike> e = bikeRepo.getBike(bike.getId());
            if (e.isPresent()) {
                if (bike.getName() != null) {
                    e.get().setName(bike.getName());
                }
                if (bike.getBrand() != null) {
                    e.get().setBrand(bike.getBrand());
                }
                if (bike.getYear() != null) {
                    e.get().setYear(bike.getYear());
                }
                if (bike.getDescription() != null) {
                    e.get().setDescription(bike.getDescription());
                }
                if (bike.getCategory() != null) {
                    e.get().setCategory(bike.getCategory());
                }
                bikeRepo.save(e.get());
                return e.get();
            } else {
                return bike;
            }
        } else {
            return bike;
        }
    }


    public boolean deleteBike(int id) {
        boolean del = getBike(id).map(bike -> {
            bikeRepo.delete(bike);
            return true;
        }).orElse(false);
        return del;
    }

}
