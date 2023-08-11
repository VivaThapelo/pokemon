package dev.thapelo.pokemon_details.stats;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StatsServiceTest {
    @Autowired
    private StatsService statsService;

    /**
     * Method under test: {@link StatsService#getStatsByPokemonId(Long)}
     */
    @Test
    void testGetStatsByPokemonId() {
        assertTrue(statsService.getStatsByPokemonId(2L).isEmpty());
    }
}

