package com.example.music;

import com.example.music.user.UserCredentials;
import com.example.music.user.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class MusicApplication implements CommandLineRunner {

	UserCredentialsRepository repository;

	@Value("${key}")
	protected String key;


	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
