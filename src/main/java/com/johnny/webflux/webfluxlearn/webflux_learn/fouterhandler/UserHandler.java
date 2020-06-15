package com.johnny.webflux.webfluxlearn.webflux_learn.fouterhandler;

import com.johnny.webflux.webfluxlearn.webflux_learn.mongo_domain.User;
import com.johnny.webflux.webfluxlearn.webflux_learn.mongo_repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 用户 Handler
 *
 * @author johnny
 * @create 2020-03-03 下午1:11
 **/
@Component
public class UserHandler {


    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;


    public Mono<ServerResponse> getAll(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.findAll() , User.class);
    }

}