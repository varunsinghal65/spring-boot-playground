package com.varun.playground;

import com.varun.playground.springAop.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlaygroundApplicationTests {

	@Autowired
	ShipmentService shipmentService;

	@Test
	void contextLoads() {
	}

	@Test
	void testBeforeLog() {
		shipmentService.shipStuff("Varun", "Singhal");
	}

}
