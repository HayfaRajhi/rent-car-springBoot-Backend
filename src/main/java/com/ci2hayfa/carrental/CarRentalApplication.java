package com.ci2hayfa.carrental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ci2hayfa.carrental.business.services.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class CarRentalApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}

}
