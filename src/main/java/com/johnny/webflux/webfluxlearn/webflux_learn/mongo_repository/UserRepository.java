package com.johnny.webflux.webfluxlearn.webflux_learn.mongo_repository;

import com.johnny.webflux.webfluxlearn.webflux_learn.mongo_domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * User 对应 Mongo的repository
 *
 * @author johnny
 * @create 2020-02-29 下午9:38
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {



}