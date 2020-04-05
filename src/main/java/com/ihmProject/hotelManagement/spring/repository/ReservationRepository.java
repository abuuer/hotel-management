/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.repository;

import com.ihmProject.hotelManagement.spring.bean.Reservation;

import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anoir
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByReference(String reference);
//
//    Reservation findBycheckIn(Date checkIn);
//
//    Reservation findBycheckOut(Date checkOut);

    Reservation findByClientCode(String clientCode);
    List<Reservation> findByUserName (String userName);
}
