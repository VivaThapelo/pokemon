package dev.thapelo.pokemon;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
