package com.bike.retoBikes.Controller;

import com.bike.retoBikes.Model.DTOs.CountClient;
import com.bike.retoBikes.Model.DTOs.CountStatus;
import com.bike.retoBikes.Model.Reservation;
import com.bike.retoBikes.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId) {
        return reservationService.getReservation(reservationId);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return reservationService.deleteReservation(id);
    }

    //reto5
    @GetMapping("/report-clients")
    public List<CountClient> getClientsFrequents(){
        return reservationService.getClientsFrequents();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReportReservationBetweenDates(@PathVariable ("dateOne") String dateOne, @PathVariable ("dateTwo") String dateTwo){
        return reservationService.getReservationBetweenDates(dateOne,dateTwo);
    }
    @GetMapping("/report-status")
    public CountStatus getReportStatus(){
        return reservationService.getReservationStatus();
    }
}
