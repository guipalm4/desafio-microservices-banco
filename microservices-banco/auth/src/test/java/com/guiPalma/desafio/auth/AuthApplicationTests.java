package com.guiPalma.desafio.auth;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AuthApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
    public void test() {
        System.out.println(new BCryptPasswordEncoder().encode("gomes1204"));
    }


}
