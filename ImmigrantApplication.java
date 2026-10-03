// Added by Shane Birckhead 
// main method to run Spring Boot application
package edu.gmu.cs321;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "edu.gmu.cs321.model")
public class ImmigrantApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImmigrantApplication.class, args);
    }
}
