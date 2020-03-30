/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Client;
import com.ihmProject.hotelManagement.spring.repository.ClientRepository;
import com.ihmProject.hotelManagement.spring.service.fac.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class ClientImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findByCode(String code) {
        return clientRepository.findByCode(code);
    }

    @Override
    public List<Client> findByAdress(String adress) {
        return clientRepository.findByAdress(adress);
    }

    @Override
    public List<Client> findByAge(String age) {
        return clientRepository.findByAge(age);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public int deleteByCode(String code) {
        Client foundedClient = clientRepository.findByCode(code);
        if (foundedClient == null) {
            return -1;
        } else {
            clientRepository.deleteByCode(code);
            return 1;
        }
    }

    @Override
    public int save(Client client) {
        Client foundedClient = clientRepository.findByCode(client.getCode());
        if (foundedClient != null) {
            return -1;
        } else {
            clientRepository.save(client);
            return 1;
        }
    }

}
