package com.johnny.webflux.webfluxlearn.webflux_learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * WebFlux demo1
 *
 * @author johnny
 * @create 2020-02-25 下午10:08
 **/
@RestController
@Slf4j
public class WebFluxTest {


    @RequestMapping("/helloSpringMvc")
    public String helloSpringMvc() {
        log.info("【helloSpringMvc start】");
        doSomeThing();
        log.info("【helloSpringMvc end】");
        return "Hello SpringMVC";
    }

    @RequestMapping("/helloWebFlux")
    public Mono<String> helloWebFlux() {
        log.info("【helloWebFlux start】");

        //这段代码的doSomeThing是在线程池中执行的，不是Web（Tomcat）容器线程，Tomcat容器线程直接返回了
        //而这个result Mono是一个发布者，它的订阅者是SpringWebFlux去实现的，当这个发布者在其他线程中执行完毕后得到数据（即产生数据并提交）后
        //则SpringWebFlux对应的实现的订阅者 会收到数据，并且把数据放到Http Response 返回给前端
        Mono<String> result = Mono.fromSupplier(() -> doSomeThing());

        log.info("【helloWebFlux end】");
        return result;
    }

    @RequestMapping("/hellMono")
    public Mono<String> hellMono(){
        return Mono.just("Hello Mono");
    }

    @RequestMapping("/hellFlux")
    public Flux<User> hellFlux(){
        User user = new User(1,"johnny");
        User user2 = new User(2,"lucy");
        return Flux.just(user, user2);
    }

    class User{
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private String doSomeThing() {

        try {
            //模拟耗时操作
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello WebFlux";
    }


}