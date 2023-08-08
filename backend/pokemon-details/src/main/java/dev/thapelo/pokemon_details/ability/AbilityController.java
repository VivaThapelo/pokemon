package dev.thapelo.pokemon_details.ability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/details/abilities")
public record AbilityController(AbilityService abilityService) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ability> getAbilities(@PathVariable Long id) {
        return abilityService.getAbilities(id);
    }
}
