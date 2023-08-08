package dev.thapelo.pokemon_details;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import dev.thapelo.pokemon_details.ability.Ability;
import dev.thapelo.pokemon_details.stats.Stats;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class DetailsResponse {
    private long id;
    private String imageUrl;
    private Integer height;
    private Integer weight;
    private Ability[] abilities;
    private Stats[] stats;

    @JsonProperty("sprites")
    private void extractImageUrl(Map<String, JsonNode> sprites) {
        imageUrl = sprites.get("other").get("official-artwork").get("front_shiny").toString();
    }
}

