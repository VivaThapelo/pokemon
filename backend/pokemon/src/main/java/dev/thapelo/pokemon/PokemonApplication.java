package dev.thapelo.pokemon;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PokemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    static class Seeder {
        private final ThreadService service;

        @PostConstruct
        public void init() {
            new Thread(service::dataSeeding).start();
        }
    }

}