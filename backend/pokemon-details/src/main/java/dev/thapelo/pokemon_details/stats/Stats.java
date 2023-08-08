package dev.thapelo.pokemon_details.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "stats")
@ToString
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long pokemonId;
    private String name;
    private Integer base_stat;
    private Integer effort;

    @JsonProperty("stat")
    private void extractName(Map<String, String> stat) {
        name = stat.get("name");
    }
}