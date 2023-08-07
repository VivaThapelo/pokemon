package dev.thapelo.pokemon;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "pokemon")
@Getter
@Setter
public class Pokemon {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;
    private String name;
    private String imageUrl;
}
