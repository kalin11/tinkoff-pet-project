package edu.tinkoff.tinkoffbackendacademypetproject;

import edu.tinkoff.tinkoffbackendacademypetproject.configuration.StorageProperties;
import edu.tinkoff.tinkoffbackendacademypetproject.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class TinkoffBackendAcademyPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkoffBackendAcademyPetProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }

}
