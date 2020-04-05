/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;



import java.util.List;
import java.util.Observable;

import com.ihmProject.hotelManagement.spring.bean.Reservation;

import javafx.collections.ObservableList;

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
    List<Reservation> findByUserName (String userName);
    
    
}
