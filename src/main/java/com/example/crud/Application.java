package com.example.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Jack", "Bauer", "jack.bauer@example.com"));
            repository.save(new Customer("Chloe", "O'Brian", "chloe.obrian@example.com"));
            repository.save(new Customer("Kim", "Bauer", "kim.bauer@example.com"));
            repository.save(new Customer("David", "Palmer", "david.palmer@example.com"));
            repository.save(new Customer("Michelle", "Dessler", "michelle.dessler@example.com"));

            log.info("Customers found with findAll():");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }

            Customer customer = repository.findById(1L).get();
            log.info("Customer found with findById(1L):");
            log.info(customer.toString());

            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            repository.findByLastNameStartsWithIgnoreCase("Bauer").forEach(bauer -> log.info(bauer.toString()));
        };
    }
}
