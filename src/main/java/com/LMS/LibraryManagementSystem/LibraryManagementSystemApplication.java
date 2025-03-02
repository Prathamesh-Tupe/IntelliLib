package com.LMS.LibraryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"Admin_Librarian", "Student_Staff"})
@EnableJpaRepositories(basePackages = {"Admin_Librarian.repositories", "Student_Staff.repositories"})
@EntityScan(basePackages = {"Admin_Librarian.entities", "Student_Staff.entities"})
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
		System.out.println("Spring boot working sucessfully");
	}
}
