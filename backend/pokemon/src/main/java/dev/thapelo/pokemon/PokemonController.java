package dev.thapelo.pokemon;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/pokemons")
public record PokemonController(PokemonService pokemonService) {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Pokemon> getPokemons() {
        return pokemonService.getAll();
    }

}
