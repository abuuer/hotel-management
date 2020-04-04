package com.ihmProject.hotelManagement.spring.rest;


import com.ihmProject.hotelManagement.spring.bean.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.fac.ReservationService;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("hotels/Reservation")
public class ReservationRest {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reference/{reference}")
    public Reservation findByReference(@PathVariable String reference) {
        return reservationService.findByReference(reference);
    }

//    @GetMapping("/checkIn/{checkIn}")
//    public Reservation findByCheckIn(@PathVariable Date checkIn) {
//        return reservationService.findByCheckIn(checkIn);
//    }
//
//    @GetMapping("/checkOut/{checkOut}")
//    public Reservation findByCheckOut(@PathVariable Date checkOut) {
//        return reservationService.findByCheckOut(checkOut);
//    }

    @GetMapping("/clientCode/{clientCode}")
    public Reservation findByClientCode(@PathVariable String clientCode) {
        return reservationService.findByClientCode(clientCode);
    }

    @PostMapping("/")
    public int save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

 

   

}
