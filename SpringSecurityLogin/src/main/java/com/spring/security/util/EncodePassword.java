package com.spring.security.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncodePassword {
	public static void main(String[] args) {
		String pwd = "biswa";
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		System.out.println(encode.encode(pwd));
	}
}
