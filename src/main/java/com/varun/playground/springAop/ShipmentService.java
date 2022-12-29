package com.varun.playground.springAop;

import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Log(param1 = "#p1", param2 = "#p2")
    public void shipStuff(String p1, String p2) {
        System.out.println("In shipping service");
    }
}
