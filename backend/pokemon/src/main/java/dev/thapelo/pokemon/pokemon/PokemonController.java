package dev.thapelo.pokemon.pokemon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public record PokemonController(PokemonService pokemonService) {

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<Pokemon> getPokemons(){
            return pokemonService.getAll();
    }

}
