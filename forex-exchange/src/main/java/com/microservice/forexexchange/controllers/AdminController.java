package com.microservice.forexexchange.controllers;

import java.util.List;

import com.microservice.forexexchange.models.ExchangeValue;
import com.microservice.forexexchange.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // create exchange value
    @PostMapping("/create/exchange")
    public ResponseEntity<?> createNewExchangeValue(@RequestBody ExchangeValue body){
        return adminService.createExchangeValue(body);
    }


    // get all exchnage value
    @GetMapping("/exchange/values")
    public List<ExchangeValue> getAllExchangeValues(){
        return adminService.fetchAllExchangeValues();
    }


    // edit exchange value record
    @GetMapping("/edit/exchange/{id}")
    public ResponseEntity<?> editExchangeRecord(@PathVariable("id") Long id){
        return adminService.editExchangeValue(id);
    }


    // update exchange value record
    @PutMapping("/update/exchange/{id}")
    public ResponseEntity<?> updateExchangeRecord(@PathVariable("id") Long id, @RequestBody ExchangeValue body){
        return adminService.updateExchangeValue(id, body);
    }

    // delete exchange value record
    @DeleteMapping("/delete/exchange/{id}")
    public ResponseEntity<?> deleteExchangeValue(@PathVariable("id") Long id){
        return adminService.deleteExchangeValueRecord(id);
    }  
    
}