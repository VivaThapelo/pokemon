package dev.thapelo.pokemon_details;


import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokemon {
    private Long id;
    private String name;
    private String url;
}
