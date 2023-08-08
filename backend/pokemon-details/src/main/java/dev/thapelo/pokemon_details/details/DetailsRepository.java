package dev.thapelo.pokemon_details.details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DetailsRepository extends JpaRepository<Details, Long> {

    @Query(value = "SELECT image_url FROM details WHERE id=?",nativeQuery = true)
    String findByImageById(Long Id);
}