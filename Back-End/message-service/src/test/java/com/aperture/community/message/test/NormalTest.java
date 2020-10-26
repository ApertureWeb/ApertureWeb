package com.aperture.community.message.test;

import io.vertx.core.Vertx;

public class NormalTest {
    public static void main(String[] args) {
        NormalTest test = new NormalTest();
        test.test();
    }

    public void test(){
        for (int i = 0; i < 10; i++) {
            try{
                throw new Exception();
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
        }
    }

}
