package com.bike.retoBikes.Repository.crudRepository;

import com.bike.retoBikes.Model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
    //consulta para los clientes con mayor numero de reservas
    //primero me devuelven el cliente y en 1. un number
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client) DESC ")
    public List<Object[]>countTotalReservationByClient();

    // ejemplo de sentencia con metodos mas simplificado
    //  Select * from Reservation where idReservation between valorA and valorB;
    //public List<Reservation>findAllByIdReservationBetweenAnd(int valorA, int valorB);

    //cantidad de reservas en un tiempo especifico, una fecha a otra
    //select * form Reservation where startDate after dateOne and devolutionDate before dateTwo
    public List<Reservation>findAllByStartDateAfterAndDevolutionDateBefore(Date dateOne, Date dateTwo);

    //cantidad de reservas completadas y canceladas
    //Select count(*) from Reservation where status =variable;
    public List<Reservation> findAllByStatus(String status);


}
