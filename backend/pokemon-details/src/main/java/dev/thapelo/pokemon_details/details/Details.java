package dev.thapelo.pokemon_details.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "details")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    @Id
    @JsonProperty("id")
    private long id;
    private String imageUrl;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("sprites")
    private void extractImageUrl(Map<String, JsonNode> sprites) {
        imageUrl = sprites.get("other").get("official-artwork").get("front_shiny").toString();
    }
}