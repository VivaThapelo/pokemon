package dev.thapelo.pokemon;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pokemon")
public class Pokemon {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    private String name;
    private String url;
}
