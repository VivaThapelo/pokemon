package dev.thapelo.pokemon.pokemon;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {PokemonService.class})
@ExtendWith(SpringExtension.class)
class PokemonServiceTest {
    @MockBean
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonService pokemonService;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link PokemonService#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        when(pokemonRepository.findAll()).thenReturn(pokemonList);
        List<Pokemon> actualAll = pokemonService.getAll();
        assertSame(pokemonList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(pokemonRepository).findAll();
    }
}

