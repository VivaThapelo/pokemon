package dev.thapelo.pokemon_details.stats;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/details/stats")
public record StatsController(StatsService statsService) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<Stats> getStatsByPokemonId(@PathVariable Long id) {
        return statsService.getStatsByPokemonId(id);
    }
}
