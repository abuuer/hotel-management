/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;

import com.ihmProject.hotelManagement.spring.bean.Client;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface ClientService  {
     Client findByCode(String code);
     List<Client> findByAdress(String adress); 
     List<Client> findByAge(String age); 
     List<Client> findAll(); 
     int deleteByCode(String code);
     int save(Client client);    
}
