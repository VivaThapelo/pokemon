package dev.thapelo.pokemon_details.ability;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "ability")
@ToString
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long pokemonId;
    private String name;

    @JsonProperty("ability")
    private void extractName(Map<String, String> ability) {
        name = ability.get("name");
    }
}
