package com.jenkins.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("testing")
class HelloWorldTests {

	@Test
	void contextLoads() {
	}

}
