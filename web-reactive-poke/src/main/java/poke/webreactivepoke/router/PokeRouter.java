package poke.webreactivepoke.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import poke.webreactivepoke.datasource.PokeAPI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class PokeRouter {

    @Bean
    RouterFunction<ServerResponse> route(PokeAPI pokeAPI){
        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), pokeAPI::hello);

//        return RouterFunctions
//                .route(GET("/poke"), pokeAPI.pokemonDatasource());
    }

}
