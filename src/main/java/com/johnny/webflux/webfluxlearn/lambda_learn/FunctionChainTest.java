package com.johnny.webflux.webfluxlearn.lambda_learn;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @author johnny
 * @create 2020-01-23 上午9:53
 **/

class MyMoney {

    public MyMoney(Integer money) {
        this.money = money;
    }

    private Integer money;

    public void printMoney(Function<Integer, String> function) {
        System.out.println("我的存款: " + function.apply(this.money));
    }
}

public class FunctionChainTest {

    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(9999999);
        Function<Integer, String> moneyFormat = money -> new DecimalFormat("#,###").format(money);
        Function<Integer, String> thenFormat = moneyFormat.andThen((s -> "人民币：" + s));
        myMoney.printMoney(thenFormat);


        Function<Integer, String> integerStringFunction = i -> new DecimalFormat("#,###").format(i);


    }

}