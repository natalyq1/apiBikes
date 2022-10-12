package com.bike.retoBikes.Repository;

import com.bike.retoBikes.Model.Client;
import com.bike.retoBikes.Model.DTOs.CountClient;
import com.bike.retoBikes.Repository.crudRepository.ReservationCrudRepository;
import com.bike.retoBikes.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepo {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }

    //reto 5
    //top clients report
    public List <CountClient> getClientsFrequents(){
        List<CountClient> response =new ArrayList<>();

        List<Object[]> reporte = reservationCrudRepository.countTotalReservationByClient();
        for(int i =0;i<reporte.size(); i++){
            response.add(new CountClient((Long)reporte.get(i)[1], (Client) reporte.get(i)[0]));
        }
        return response;
    }
//reservas durante un periodo de tiempo
    public List<Reservation> getReservationBetweenDates(Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndDevolutionDateBefore(a, b);
}
 // estados de reserva
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

}
