/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.repository;

import com.ihmProject.hotelManagement.spring.bean.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anoir
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

     Client findByCode(String code);
     List<Client> findByAdress(String adress); 
     List<Client> findByAge(String age); 
     int deleteByCode(String code);
     
}
