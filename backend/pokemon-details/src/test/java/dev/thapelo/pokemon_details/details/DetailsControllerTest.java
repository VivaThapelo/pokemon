package dev.thapelo.pokemon_details.details;

import static org.mockito.Mockito.when;

import java.util.Optional;

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

@ContextConfiguration(classes = {DetailsController.class, DetailsService.class})
@ExtendWith(SpringExtension.class)
class DetailsControllerTest {
    @Autowired
    private DetailsController detailsController;

    @MockBean
    private DetailsRepository detailsRepository;

    /**
     * Method under test: {@link DetailsController#getDetailsById(Long)}
     */
    @Test
    void testGetDetailsById() throws Exception {
        Details details = new Details();
        details.setHeight(1);
        details.setId(1L);
        details.setImageUrl("https://example.org/example");
        details.setWeight(3);
        Optional<Details> ofResult = Optional.of(details);
        when(detailsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/pokemon/details/42");
        MockMvcBuilders.standaloneSetup(detailsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"imageUrl\":\"https://example.org/example\",\"id\":1,\"height\":1,\"weight\":3}"));
    }
}

