package com.johnny.webflux.webfluxlearn.webflux_learn;

import com.johnny.webflux.webfluxlearn.webflux_learn.mongo_domain.User;
import com.johnny.webflux.webfluxlearn.webflux_learn.mongo_repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

/**
 * UserController测试 reactive Mongodb crud
 *
 * @author johnny
 * @create 2020-02-29 下午9:40
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * 推荐构造器方式注入
     */
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * http://localhost:9001/user/
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> addUser(@Valid @RequestBody User user) {
        //根据实际情况设置id 是否为Null
        user.setId(null);
        return userRepository.save(user);
    }

    /**
     * 根据ID查询User 查不到返回404
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id) {

        return userRepository.findById(id)
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 查询所有数据 一次返回
     *
     * @return
     */
    @GetMapping("/")
    public Flux<User> findAll() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userRepository.findAll();
    }


    /**
     * 查询 所有数据 按照SSE形式返回
     *
     * @return
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindAll() {
        return userRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

//        return userRepository.findById(user.getId())
//                .map(u -> {
//                    u.setAge(user.getAge());
//                    u.setName(user.getName());
//                    return this.userRepository.save(u); //这个就是返回Mono
//                }) //如果使用map 则这里相当于得到 Mono<Mono<User>>

    //.map(u -> new ResponseEntity<User>(u , HttpStatus.OK))
    //.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    /**
     * flagMap 返回Mono
     * map 返回 Mono<u> - > Mono<r> 就是将Mono内部的数据转换成其他数据
     *
     * @param user
     * @return
     */
    @PutMapping("/")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user) {
        return userRepository.findById(user.getId())
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 删除 用户根据ID
     * 存在用户则删除 返回200
     * 不存在用户 返回404
     *
     * @param id: id
     * @return : Mono<ResponseEntity<Void>>
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String id) {

        return userRepository.findById(id)
                //flatMap 是要操作数据的时候用
                .flatMap(u -> userRepository.deleteById(id))
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 测试基于WebClient的请求方式
     */
    @RequestMapping("/webclient")
    public void WebClientTest(){

        System.out.println(Thread.currentThread().getName());

        System.out.println("webclient start");
        WebClient webClient = WebClient.create();

        //这段代码是异步的 非阻塞的
        Flux<User> userFlux = webClient.get().uri("http://localhost:9001/user/")
                .retrieve()
                .bodyToFlux(User.class);

        userFlux.subscribe(System.out::println);

        System.out.println("webclient end");


        /**
         * 打印如下
         *
         *
         * webclient start
         * webclient end
         * 2020-03-04 08:59:10.019 DEBUG 29384 --- [ctor-http-nio-5] o.s.d.m.core.ReactiveMongoTemplate       : find using query: {} fields: Document{{}} for class: class com.johnny.webflux.webfluxlearn.webflux_learn.mongo_domain.User in collection: user
         * 2020-03-04 08:59:10.151  INFO 29384 --- [ntLoopGroup-2-2] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:56}] to 127.0.0.1:27017
         * User(id=5e5a6bbd514391729bcbf930, name=johnny, age=23)
         */
    }

}