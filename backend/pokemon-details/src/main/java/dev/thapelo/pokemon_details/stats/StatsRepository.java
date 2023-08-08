package dev.thapelo.pokemon_details.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(value = "SELECT * FROM stats WHERE pokemon_id=?",nativeQuery = true)
    List<Stats> findByPokemonId(Long pokemonId);
}