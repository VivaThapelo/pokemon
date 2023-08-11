package dev.thapelo.pokemon.pokemon;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "pokemon")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    private String name;
    private String url;
}
