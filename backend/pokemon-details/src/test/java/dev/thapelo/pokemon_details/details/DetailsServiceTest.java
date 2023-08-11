package dev.thapelo.pokemon_details.details;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DetailsServiceTest {
    @Autowired
    private DetailsService detailsService;

    /**
     * Method under test: {@link DetailsService#getDetailsById(Long)}
     */
    @Test
    void testGetDetailsById() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        detailsService.getDetailsById(1L);
    }

    /**
     * Method under test: {@link DetailsService#getDetailsById(Long)}
     */
    @Test
    void testGetDetailsById2() {
        assertFalse(detailsService.getDetailsById(2L).isPresent());
    }

    /**
     * Method under test: {@link DetailsService#getImageUrlById(Long)}
     */
    @Test
    void testGetImageUrlById() {
        assertNull(detailsService.getImageUrlById(1L));
    }
}

