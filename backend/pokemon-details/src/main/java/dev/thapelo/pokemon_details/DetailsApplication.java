package dev.thapelo.pokemon_details;

import dev.thapelo.pokemon_details.ability.AbilityRepository;
import dev.thapelo.pokemon_details.details.Details;
import dev.thapelo.pokemon_details.details.DetailsRepository;
import dev.thapelo.pokemon_details.stats.StatsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class DetailsApplication {
    private final RestTemplate restTemplate;

    public final AbilityRepository abilityRepository;

    public final StatsRepository statsRepository;

    @Value("${POKEMON_SERVICE_URL}")
    public String POKEMON_SERVICE_ENDPOINT;

    public DetailsApplication(RestTemplate restTemplate, AbilityRepository abilityRepository, StatsRepository statsRepository) {
        this.restTemplate = restTemplate;
        this.abilityRepository = abilityRepository;
        this.statsRepository = statsRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DetailsApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    class Seeder {
        private final ThreadService service;
        @PostConstruct
        public void init() {
            new Thread(service::dataSeeding).start();
        }
    }

    @Component
    class ThreadService {

        private final DetailsRepository detailsRepository;

        public ThreadService(DetailsRepository detailsRepository) {
            this.detailsRepository = detailsRepository;
        }

        void dataSeeding(){
            log.info(POKEMON_SERVICE_ENDPOINT);
            ResponseEntity<Pokemon[]> response = restTemplate.exchange(
                    POKEMON_SERVICE_ENDPOINT,
                    HttpMethod.GET,
                    null,
                    Pokemon[].class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Pokemon[] pokemonsList = response.getBody();
                if (pokemonsList != null) {
                    Arrays.stream(pokemonsList).sequential().forEach( pokemon -> {
                        DetailsResponse detailsResponse = restTemplate.getForObject(pokemon.getUrl(),DetailsResponse.class);
                        if (detailsResponse != null) {
                            List.of(detailsResponse).forEach(detail -> {
                                Arrays.stream(detail.getAbilities()).forEachOrdered( ability -> ability.setPokemonId(detail.getId()));
                                Arrays.stream(detail.getStats()).forEachOrdered( stat -> stat.setPokemonId(detail.getId()));
                                Details basicDetails = Details.builder().weight(detail.getWeight()).id(detail.getId()).height(detail.getHeight()).imageUrl(detail.getImageUrl()).build();
                                detailsRepository.save(basicDetails);
                            });
                            abilityRepository.saveAll(Arrays.asList(detailsResponse.getAbilities()));
                            statsRepository.saveAll(Arrays.asList(detailsResponse.getStats()));
                        }
                    });
                }
            }
        }

    }
}
