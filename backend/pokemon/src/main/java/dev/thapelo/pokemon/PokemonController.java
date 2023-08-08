package dev.thapelo.pokemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
