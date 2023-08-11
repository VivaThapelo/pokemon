package dev.thapelo.pokemon_details;

import dev.thapelo.pokemon_details.ability.AbilityRepository;
import dev.thapelo.pokemon_details.details.Details;
import dev.thapelo.pokemon_details.details.DetailsRepository;
import dev.thapelo.pokemon_details.stats.StatsRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
class ThreadService {
    private final RestTemplate restTemplate;

    private final DetailsRepository detailsRepository;


    public final AbilityRepository abilityRepository;

    public final StatsRepository statsRepository;

    @Value("${POKEMON_SERVICE_URL}")
    public String POKEMON_SERVICE_ENDPOINT;

    public ThreadService(AbilityRepository abilityRepository, StatsRepository statsRepository, RestTemplate restTemplate, DetailsRepository detailsRepository) {
        this.abilityRepository = abilityRepository;
        this.statsRepository = statsRepository;
        this.restTemplate = restTemplate;
        this.detailsRepository = detailsRepository;
    }


    @SneakyThrows
    void dataSeeding() {
        ResponseEntity<Pokemon[]> response = restTemplate.exchange(
                POKEMON_SERVICE_ENDPOINT,
                HttpMethod.GET,
                null,
                Pokemon[].class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Pokemon[] pokemonsList = response.getBody();
            if (pokemonsList != null) {
                Arrays.stream(pokemonsList).sequential().forEach(pokemon -> {
                    DetailsResponse detailsResponse = restTemplate.getForObject(pokemon.getUrl(), DetailsResponse.class);
                    if (detailsResponse != null) {
                        List.of(detailsResponse).forEach(detail -> {
                            Arrays.stream(detail.getAbilities()).forEachOrdered(ability -> ability.setPokemonId(detail.getId()));
                            Arrays.stream(detail.getStats()).forEachOrdered(stat -> stat.setPokemonId(detail.getId()));
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
