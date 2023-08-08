package dev.thapelo.pokemon_details.details;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/details")
public record DetailsController(DetailsService detailsService) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Optional<Details> getDetailsById(@PathVariable Long id) {
        return detailsService.getDetailsById(id);
    }

    @GetMapping("/image/{id}")
    @ResponseStatus(HttpStatus.OK)
    String getPokemonImage(@PathVariable Long id) {
        return detailsService.getImageUrlById(id);
    }
}
