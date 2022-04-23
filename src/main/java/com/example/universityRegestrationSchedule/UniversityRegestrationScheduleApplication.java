package com.example.universityRegestrationSchedule;


import com.example.universityRegestrationSchedule.Models.Role;
import com.example.universityRegestrationSchedule.Repository.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityRegestrationScheduleApplication {
	public static final Logger log = LoggerFactory.getLogger(UniversityRegestrationScheduleApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(UniversityRegestrationScheduleApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(RoleRepo repository) {
		if (repository.findAll().size() == 0) {
			return args -> {
				log.info("Preloading " + repository.save(new Role("STUDENT")));
				log.info("Preloading " + repository.save(new Role("INSTRUCTOR")));
				log.info("Preloading " + repository.save(new Role("ADMIN")));
			};
		}
		return args -> {
			log.info("All roles is existing");
		};
	}
}

