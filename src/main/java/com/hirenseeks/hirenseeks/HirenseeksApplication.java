package com.hirenseeks.hirenseeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hirenseeks.hirenseeks.user.User;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HirenseeksApplication {

	public static void main(String[] args) {
		try {
            Dotenv dotenv = Dotenv.configure().load();
            System.out.println(".env Found");
            
            System.setProperty("URL", dotenv.get("URL"));
            System.out.println("URL Found");
            
            System.setProperty("USER", dotenv.get("USER"));
            System.out.println("USER Found");
            
            System.setProperty("PASSWORD", dotenv.get("PASSWORD"));
            System.out.println("PASSWORD Found");
            
            System.setProperty("DRIVER_CLASS_NAME", dotenv.get("DRIVER_CLASS_NAME"));
            System.out.println("DRIVER_CLASS_NAME Found");
            
            System.setProperty("SHOW_SQL", dotenv.get("SHOW_SQL"));
            System.out.println("SHOW_SQL Found");
            
            System.setProperty("HIBERNATE_FORMAT_SQL", dotenv.get("HIBERNATE_FORMAT_SQL"));
            System.out.println("HIBERNATE_FORMAT_SQL Found");
            
            System.setProperty("INCLUDE_MESSAGE", dotenv.get("INCLUDE_MESSAGE"));
            System.out.println("INCLUDE_MESSAGE Found");
        
        } catch (Exception e) {
            System.out.println("Error loading .env file: " + e.getMessage());
            // Handle the exception as needed
            return;
        }
		SpringApplication.run(HirenseeksApplication.class, args);
        // User u = new User();
        // System.out.println(u.toString());

	}

}
