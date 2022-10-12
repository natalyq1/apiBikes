package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.DTOs.CountClient;
import com.bike.retoBikes.Model.DTOs.CountStatus;
import com.bike.retoBikes.Model.Reservation;
import com.bike.retoBikes.Repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepo reservationRepo;

    public List<Reservation> getAll(){
        return reservationRepo.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return reservationRepo.getReservation(reservationId);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return reservationRepo.save(reservation);
        }else{
            Optional<Reservation> e= reservationRepo.getReservation(reservation.getIdReservation());
            if(e.isPresent()){
                return reservation;
            }else{
                return reservationRepo.save(reservation);
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= reservationRepo.getReservation(reservation.getIdReservation());
            if(e.isPresent()){
                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepo.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation (int id){
        boolean del = getReservation(id).map(reservation -> {
            reservationRepo.delete(reservation);
            return true;
        }).orElse(false);
        return del;
    }

    //reto5
    public List<CountClient> getClientsFrequents(){
        return reservationRepo.getClientsFrequents();
    }

    public List<Reservation> getReservationBetweenDates(String dateA, String dateB){
        SimpleDateFormat parser= new SimpleDateFormat("yyyy-MM-dd");
        Date a= new Date();
        Date b= new Date();
        try {
            a= parser.parse(dateA);
            b= parser.parse(dateB);
        }catch(ParseException error){
            error.printStackTrace();
        }
        //validar
        if (a.before(b)){
            return reservationRepo.getReservationBetweenDates(a,b);
        }else{
            return new ArrayList<>();
        }
    }
    //reportar estado de canceladas y completadas
    public CountStatus getReservationStatus(){
      List<Reservation> reservationsCompleted = reservationRepo.getReservationByStatus("completed");
        List<Reservation> reservationsCancelled = reservationRepo.getReservationByStatus("cancelled");

        return new CountStatus((long) reservationsCompleted.size(), (long) reservationsCancelled.size());
    }

}
