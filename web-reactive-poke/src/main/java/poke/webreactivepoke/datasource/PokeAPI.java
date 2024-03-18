package poke.webreactivepoke.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PokeAPI {


    public String pokemonDatasource(){
        try (HttpClient client = HttpClient.newHttpClient()){
            HttpRequest pokeAPIRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://pokeapi.co/api/v2/pokemon/"))
                    .GET()
                    .build();

//            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(pokeAPIRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Wasup PokeAPI");
//            return new PokemonDto(response.body());
//            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//                    .body(BodyInserters.fromValue(response));
            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            return "no body!";

        }

    }


    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(pokemonDatasource()));
    }

}
