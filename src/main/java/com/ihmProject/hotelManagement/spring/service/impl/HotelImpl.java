/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.repository.HotelRepository;
import com.ihmProject.hotelManagement.spring.service.fac.HotelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class HotelImpl implements HotelService{
    
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> findByCity(String city) {
        return hotelRepository.findByCity(city);
    }

    @Override
    public List<Hotel> findByName(String name) {
        return hotelRepository.findByName(name);
    }

    @Override
    public List<Hotel> findByStars(String stars) {
        return hotelRepository.findByStars(stars);
    }

    @Override
    public Hotel findByReference(String reference) {
        return hotelRepository.findByReference(reference);
    }

    @Override
    public int deleteByReference(String reference) {
        Hotel foundedHotel = hotelRepository.findByReference(reference);
        if(foundedHotel == null){
            return -1 ;
        } else {
            hotelRepository.deleteByReference(reference);
            return 1;
        }
    }

    @Override
    public int save(Hotel hotel) {
         Hotel foundedHotel = hotelRepository.findByReference(hotel.getReference());
        if(foundedHotel != null){
            return -1 ;
        } else {
            hotelRepository.save(hotel);
            return 1;
        }
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

   
}
