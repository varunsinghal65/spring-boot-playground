package com.varun.playground.spring.aop.controller;

import com.varun.playground.spring.aop.PreAuthorize;
import com.varun.playground.spring.aop.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShipmentController {

    @PreAuthorize(userName = "T(com.varun.playground.spring.aop.services.Extractors).userNameExtractor.extract(#user)",
            userAction = "#userAction")
    @PostMapping("/shipStuff/{userAction}")
    ResponseEntity<String> shipStuff(@RequestBody User user, @PathVariable String userAction) {
        return new ResponseEntity<>("Successfully shipped item for:" + user, HttpStatus.OK);
    }

}
