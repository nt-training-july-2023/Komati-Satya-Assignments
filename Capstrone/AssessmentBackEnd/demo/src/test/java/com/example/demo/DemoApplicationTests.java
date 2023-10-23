package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	public void mainTest() {
	    assertDoesNotThrow(() -> DemoApplication.main(new String[]{}));
	}

}
