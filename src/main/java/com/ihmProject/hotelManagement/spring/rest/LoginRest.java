package com.ihmProject.hotelManagement.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.service.fac.LoginService;

@RestController
@RequestMapping("hotels/Login")
public class LoginRest {
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public List<Login> findAll() {
		return loginService.findAll();
	}

	@GetMapping("/userName/{userName}")
	public Login findByUserName(@PathVariable String userName) {
		return loginService.findByUserName(userName);
	}
	
	@PostMapping("/")
	public int save(@RequestBody Login login) {
		return loginService.save(login);
	}
	
	

}
