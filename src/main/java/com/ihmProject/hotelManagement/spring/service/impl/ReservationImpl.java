/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Client;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.bean.Reservation;
import com.ihmProject.hotelManagement.spring.repository.ReservationRepository;
import com.ihmProject.hotelManagement.spring.service.fac.ClientService;
import com.ihmProject.hotelManagement.spring.service.fac.HotelService;
import com.ihmProject.hotelManagement.spring.service.fac.ReservationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class ReservationImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private HotelService hotelService;

    @Override
    public Reservation findByReference(String reference) {
        return reservationRepository.findByReference(reference);
    }

//    @Override
//    public Reservation findByCheckIn(Date checkIn) {
//        return reservationRepository.findBycheckIn(checkIn);
//    }
//
//    @Override
//    public Reservation findByCheckOut(Date checkOut) {
//        return reservationRepository.findBycheckOut(checkOut);
//    }
    @Override
    public Reservation findByClientCode(String clientCode) {
        return reservationRepository.findByClientCode(clientCode);
    }

    @Override
    public int save(Reservation reservation) {
        Reservation foundedRes = reservationRepository.findByReference(reservation.getReference());
        Hotel foundedHotel = hotelService.findByReference(reservation.getHotel().getReference());
        Client foundedClient = clientService.findByCode(reservation.getClient().getCode());
        if (foundedRes != null) {
            return -1;
        } else if (foundedHotel == null) {
            return -2 ;
        }else if (foundedClient != null) {
            return -3 ;
        } else {
            clientService.save(reservation.getClient());
            reservation.setHotel(foundedHotel);
            reservationRepository.save(reservation);
            return 1;
        }
    }

}
