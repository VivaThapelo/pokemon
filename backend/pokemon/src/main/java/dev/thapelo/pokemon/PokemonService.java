package dev.thapelo.pokemon;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public record PokemonService(PokemonRepository pokemonRepository, RestTemplate restTemplate) {
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }
}
