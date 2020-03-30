/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.repository;

import com.ihmProject.hotelManagement.spring.bean.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anoir
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
    List<Hotel> findByCity(String city);

    List<Hotel> findByName(String name);

    List<Hotel> findByStars(String stars);

    Hotel findByReference(String reference);

    int deleteByReference(String reference);

}
