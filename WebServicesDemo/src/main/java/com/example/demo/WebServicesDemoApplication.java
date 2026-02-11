package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class WebServicesDemoApplication {

	public static void main(String[] args) {SpringApplication.run(WebServicesDemoApplication.class, args);
		Database.init();
		try{
			ItemClass.add(new ItemClass.Item(0, "Sample Item", "This is a sample item.", 9.99, 100));
			ItemClass.add(new ItemClass.Item(0, "Another Sample Item", "This is another sample item.", 4.99, 50));
			ItemClass.add(new ItemClass.Item(0, "Yet Another Sample Item", "This is yet another sample item.", 2.99, 25));
			ItemClass.add(new ItemClass.Item(0, "Last Sample Item", "This is the last sample item.", 6.99, 75));
		} catch (SQLException e) {
			System.out.println("Error adding sample item: " + e.getMessage());
		}
	}
}
