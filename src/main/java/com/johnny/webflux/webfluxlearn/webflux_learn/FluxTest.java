package com.johnny.webflux.webfluxlearn.webflux_learn;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * @author johnny
 * @create 2020-02-29 下午2:52
 **/
@Slf4j
public class FluxTest {

    public static void main(String[] args) {


        Subscriber<Integer> subscriber = new Subscriber<>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                System.out.println("订阅成功。。");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
                System.out.println("订阅方法里请求一个数据");
            }


            @Override
            public void onNext(Integer item) {
                log.info("【onNext 接受到数据 item : {}】 ", item);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("【onError 出现异常】");
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                log.info("【onComplete 所有数据接收完成】");
            }
        };

        //使用Flux 来作为Publisher
        String[] strings = {"1","2","3"};

        Flux.fromArray(strings).map(s -> Integer.parseInt(s))
                .subscribe(subscriber);

    }
}