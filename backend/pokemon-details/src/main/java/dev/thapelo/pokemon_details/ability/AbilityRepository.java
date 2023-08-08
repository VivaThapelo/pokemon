package dev.thapelo.pokemon_details.ability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbilityRepository extends JpaRepository<Ability, Long> {

    @Query(value = "SELECT * FROM ability WHERE pokemon_id=?",nativeQuery = true)
    List<Ability> findByPokemonId(Long pokemonId);
}
