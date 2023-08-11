package dev.thapelo.pokemon_details.ability;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AbilityServiceTest {
    @Autowired
    private AbilityService abilityService;

    /**
     * Method under test: {@link AbilityService#getAbilities(Long)}
     */
    @Test
    void testGetAbilities() {
        assertTrue(abilityService.getAbilities(2L).isEmpty());
    }
}

