/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;

import com.ihmProject.hotelManagement.spring.bean.Reservation;
import java.util.Date;

/**
 *
 * @author anoir
 */
public interface ReservationService {

    Reservation findByReference(String reference);
//
//    Reservation findByCheckIn(Date checkIn);
//
//    Reservation findByCheckOut(Date checkOut);

    Reservation findByClientCode(String clientCode);
    
    int save(Reservation reservation);
}
