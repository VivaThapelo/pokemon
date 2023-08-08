package dev.thapelo.pokemon_details.details;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record DetailsService(DetailsRepository detailsRepository) {

    Optional<Details> getDetailsById(Long Id) {
        return detailsRepository.findById(Id);
    }

    String getImageUrlById(Long id) {
        return detailsRepository.findByImageById(id);
    }

}
