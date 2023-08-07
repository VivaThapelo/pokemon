package dev.thapelo.pokemon;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@Slf4j
public class PokemonApplication {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public PokemonRepository pokemonRepository;

    @Value("${POKEMON_API_BASEURL}")
    public String POKEMON_API_BASEURL;

    @Value("${POKEMON_LIST_ENDPOINT}")
    public String POKEMON_LIST_ENDPOINT;

    @Value("${POKEMON_DETAIL_ENDPOINT}")
    public String POKEMON_DETAIL_ENDPOINT;

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
                    pokemonList.forEach(
                            pokemon -> {
                                log.info(POKEMON_API_BASEURL + POKEMON_DETAIL_ENDPOINT + pokemon.getName());
                                JsonNode detailResponse = restTemplate.getForObject(
                                        POKEMON_API_BASEURL + POKEMON_DETAIL_ENDPOINT + pokemon.getName(),
                                        JsonNode.class
                                );
                                if (detailResponse != null) {
                                    pokemon.setImageUrl(detailResponse.get("sprites").get("other").get("official-artwork").get("front_default").toString());
                                }
                                pokemonRepository.saveAndFlush(pokemon);
                            }
                    );
                }
            }
        }
    }

}