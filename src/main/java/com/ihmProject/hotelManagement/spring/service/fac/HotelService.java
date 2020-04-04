/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;

import com.ihmProject.hotelManagement.spring.bean.Hotel;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface HotelService {

    List<Hotel> findByCity(String city);

    Hotel findByName(String name);

    List<Hotel> findByStars(String stars);

    List<Hotel> findAll();

    Hotel findByReference(String reference);

    int deleteByReference(String reference);

    int save(Hotel hotel);

}
