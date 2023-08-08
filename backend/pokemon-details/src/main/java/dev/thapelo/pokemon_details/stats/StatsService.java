package dev.thapelo.pokemon_details.stats;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StatsService(StatsRepository statsRepository) {

    List<Stats> getStatsByPokemonId(Long pokemonId) {
        return statsRepository.findByPokemonId(pokemonId);
    }
}
