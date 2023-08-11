package dev.thapelo.pokemon_details.ability;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AbilityController.class, AbilityService.class})
@ExtendWith(SpringExtension.class)
class AbilityControllerTest {
    @Autowired
    private AbilityController abilityController;

    @MockBean
    private AbilityRepository abilityRepository;

    /**
     * Method under test: {@link AbilityController#getAbilities(Long)}
     */
    @Test
    void testGetAbilities() throws Exception {
        when(abilityRepository.findByPokemonId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/pokemon/details/abilities/42");
        MockMvcBuilders.standaloneSetup(abilityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

