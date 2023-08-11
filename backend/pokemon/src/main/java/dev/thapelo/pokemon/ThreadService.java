package dev.thapelo.pokemon;

import dev.thapelo.pokemon.pokemon.Pokemon;
import dev.thapelo.pokemon.pokemon.PokemonListResponse;
import dev.thapelo.pokemon.pokemon.PokemonRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ThreadService {
    private final RestTemplate restTemplate;
    public final PokemonRepository pokemonRepository;

    @Value("${POKEMON_API_BASEURL}")
    public String POKEMON_API_BASEURL;

    @Value("${POKEMON_LIST_ENDPOINT}")
    public String POKEMON_LIST_ENDPOINT;

    public ThreadService(PokemonRepository pokemonRepository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
    }

    @SneakyThrows
    public void dataSeeding() {
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
