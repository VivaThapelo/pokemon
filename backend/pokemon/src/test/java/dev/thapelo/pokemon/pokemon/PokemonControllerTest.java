package dev.thapelo.pokemon.pokemon;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {PokemonController.class, PokemonService.class})
@ExtendWith(SpringExtension.class)
class PokemonControllerTest {
    @Autowired
    private PokemonController pokemonController;

    @MockBean
    private PokemonRepository pokemonRepository;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link PokemonController#getPokemons()}
     */
    @Test
    void testGetPokemons() throws Exception {
        when(pokemonRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/pokemons");
        MockMvcBuilders.standaloneSetup(pokemonController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

