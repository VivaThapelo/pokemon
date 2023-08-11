package dev.thapelo.pokemon.pokemon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public record PokemonController(PokemonService pokemonService) {

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<Pokemon> getPokemons() {
        return pokemonService.getAll();
    }

}
