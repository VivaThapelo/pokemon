package dev.thapelo.pokemon;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class Other {

    @JsonProperty("official-artwork")
    private OfficialArtwork officialArtwork;


    @Getter
    static public class OfficialArtwork {

        @Getter
        @JsonProperty("front_default")
        private String frontDefault;

        @Getter
        @JsonProperty("front_default")
        private String frontShiny;

    }

}

