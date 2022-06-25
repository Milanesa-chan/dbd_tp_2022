package com.example.club;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import persistence.entities.GrupoFamiliar;
import services.interfaces.IGrupoFamiliarService;
@ComponentScan({"configuration","controllers","services","persistence.dao","persistence.entities"})
//@EnableJpaRepositories(basePackages = "persistence")
@SpringBootApplication
public class ClubApplication {



	public static void main(String[] args) {
		SpringApplication.run(ClubApplication.class, args);
	}

}
