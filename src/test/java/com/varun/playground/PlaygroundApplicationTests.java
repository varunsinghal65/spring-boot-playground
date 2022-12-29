package com.varun.playground;

import com.varun.playground.spring.aop.models.User;
import com.varun.playground.spring.aop.services.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlaygroundApplicationTests {

    @Autowired
    ShipmentService shipmentService;

    @Test
    void testPreAuthorize() {
        shipmentService.shipStuff(new User("varun", "singhal", "varunsinghal"), "CREATE_USER");
    }

}
