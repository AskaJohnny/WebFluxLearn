//package com.johnny.webflux.webfluxlearn.async_servlet;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * 同步Servlet 样例
// * @author johnny
// */
//@WebServlet(name = "Sync-SyncServlet", urlPatterns = "/sync")
//@Slf4j
//public class SyncServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("doPost sync servlet");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        long start = System.currentTimeMillis();
//
//        try {
//            //模拟业务代码处理
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        long end = System.currentTimeMillis();
//        log.info("doGet sync servlet 耗时: {} 秒", (end - start) / 1000);
//
//    }
//}
