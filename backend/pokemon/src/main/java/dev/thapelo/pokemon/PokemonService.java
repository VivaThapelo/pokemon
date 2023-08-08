package dev.thapelo.pokemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@Slf4j
public record PokemonService(PokemonRepository pokemonRepository, RestTemplate restTemplate) {
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }
}
