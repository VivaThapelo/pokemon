package dev.thapelo.pokemon.pokemon;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record PokemonService(PokemonRepository pokemonRepository, RestTemplate restTemplate) {
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }
}
