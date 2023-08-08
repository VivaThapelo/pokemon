package dev.thapelo.pokemon;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@Slf4j
public class PokemonApplication {
    private final RestTemplate restTemplate;

    public final PokemonRepository pokemonRepository;

    @Value("${POKEMON_API_BASEURL}")
    public String POKEMON_API_BASEURL;

    @Value("${POKEMON_LIST_ENDPOINT}")
    public String POKEMON_LIST_ENDPOINT;

    public PokemonApplication(RestTemplate restTemplate, PokemonRepository pokemonRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    class Seeder {
        private final ThreadService service;
        @PostConstruct
        public void init() {
            new Thread(service::dataSeeding).start();
        }
    }

    @Component
    public class ThreadService {

        @SneakyThrows
        public void dataSeeding() {
            log.info(POKEMON_API_BASEURL + POKEMON_LIST_ENDPOINT);
            ResponseEntity<PokemonListResponse> response = restTemplate.exchange(
                    POKEMON_API_BASEURL + POKEMON_LIST_ENDPOINT,
                    HttpMethod.GET,
                    null,
                    PokemonListResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                PokemonListResponse pokemonsRes = response.getBody();
                if (pokemonsRes != null) {
                    List<Pokemon> pokemonList = pokemonsRes.getResults();
                    pokemonRepository.saveAllAndFlush(pokemonList);
                }
            }
        }
    }

}