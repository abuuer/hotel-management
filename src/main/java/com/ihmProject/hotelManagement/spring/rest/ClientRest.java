package com.ihmProject.hotelManagement.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihmProject.hotelManagement.spring.bean.Client;
import com.ihmProject.hotelManagement.spring.service.fac.ClientService;

@RestController
@RequestMapping("hotels/Client")
public class ClientRest {
	@Autowired
	private ClientService clientService;

	public Client findByCode(String code) {
		return clientService.findByCode(code);
	}

	public List<Client> findByAdress(String adress) {
		return clientService.findByAdress(adress);
	}

	public List<Client> findByAge(String age) {
		return clientService.findByAge(age);
	}

	@GetMapping("/")
	public List<Client> findAll() {
		return clientService.findAll();
	}

	public int deleteByCode(String code) {
		return clientService.deleteByCode(code);
	}

	@PostMapping("/")
	public int save(@RequestBody Client client) {
		return clientService.save(client);
	}
	
	

}
