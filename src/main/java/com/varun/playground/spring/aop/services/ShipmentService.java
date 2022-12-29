package com.varun.playground.spring.aop.services;

import com.varun.playground.spring.aop.PreAuthorize;
import com.varun.playground.spring.aop.models.User;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
    @PreAuthorize(userName = "T(com.varun.playground.spring.aop.services.Extractors).userNameExtractor.extract(#user)",
            userAction = "#userAction")
    public void shipStuff(User user, String userAction) {
        System.out.println("In shipping service");
    }
}
