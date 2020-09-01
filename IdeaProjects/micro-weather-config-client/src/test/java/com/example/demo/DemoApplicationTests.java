package com.example.demo;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class DemoApplicationTests {
	@Value("${auther}")
	private String auther;

	@Test
	public void contextLoads() {
		assertEquals("Nexus", auther);

	}
}

