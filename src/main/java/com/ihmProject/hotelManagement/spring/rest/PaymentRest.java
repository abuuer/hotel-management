package com.ihmProject.hotelManagement.spring.rest;

import com.ihmProject.hotelManagement.spring.bean.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.fac.PaymentService;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("hotels/Payment")
public class PaymentRest {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public int pay(@RequestBody Payment payment) {
        return paymentService.pay(payment);
    }

    @GetMapping("/cardNumber/{cardNumber}")
    public int confirmBankingDetails(@PathVariable String cardNumber) {
        return paymentService.confirmBankingDetails(cardNumber);
    }

}
