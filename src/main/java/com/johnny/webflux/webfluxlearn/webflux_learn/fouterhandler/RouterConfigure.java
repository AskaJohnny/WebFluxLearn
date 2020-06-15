package com.johnny.webflux.webfluxlearn.webflux_learn.fouterhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author johnny
 * @create 2020-03-03 下午1:14
 **/

@Configuration
public class RouterConfigure {


    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return RouterFunctions.nest(
                RequestPredicates.path("/routeruser")
                , RouterFunctions.route(RequestPredicates.GET("/"), userHandler::getAll)
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getAll));
    }
}