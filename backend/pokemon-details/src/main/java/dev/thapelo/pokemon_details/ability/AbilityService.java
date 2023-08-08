package dev.thapelo.pokemon_details.ability;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public record AbilityService(AbilityRepository abilityRepository) {

    public List<Ability> getAbilities(Long pokemonId) {
        return abilityRepository.findByPokemonId(pokemonId);
    }
}
