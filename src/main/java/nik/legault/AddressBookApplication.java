package nik.legault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the AddressBook Spring Boot application.
 *
 * @author Nik Legault 101229919
 */
@SpringBootApplication
public class AddressBookApplication {

    /**
     * The main method which uses Spring Boot's SpringApplication.run()
     * to launch the application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }
}
