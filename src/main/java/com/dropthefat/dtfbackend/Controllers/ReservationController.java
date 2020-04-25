package com.dropthefat.dtfbackend.Controllers;

import java.util.List;

import com.dropthefat.dtfbackend.Models.Reservation;
import com.dropthefat.dtfbackend.Models.Response;
import com.dropthefat.dtfbackend.Services.ReservationServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    ReservationServices reservationServices;

    // Get all reservation
    @RequestMapping(value = "/reservation", method = RequestMethod.GET) 
    public List<Reservation> getReservation() {
        try {
            List<Reservation> result = reservationServices.getAllReservation();
            return result;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // Get reservation with id
    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable(value="id") String id){
        try{
            Reservation result = reservationServices.getReservationById(id);
            return result;
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    // Add new reservation
    @PostMapping(path = "/reservation", consumes = "application/json", produces = "application/json")
    public Response addEmployee(@RequestBody Reservation reservation){
        try{
            Response res = reservationServices.addReservation(reservation);
            logger.info("Add Reservation id = " + res.getDocId() + " success!");
            return res;
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    // Update existing reservation
    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT)
    public Response updateReservation(@PathVariable(value="id") String id, @RequestBody Reservation reservation){
        try{
            return reservationServices.updateReservation(id, reservation);
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    // Delete existing reservation
    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
    public Response deleteReservation(@PathVariable(value="id") String id){
        try{
            return reservationServices.deleteReservation(id);
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

}