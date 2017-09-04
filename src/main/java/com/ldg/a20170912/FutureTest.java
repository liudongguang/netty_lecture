package com.ldg.a20170912;

import java.util.concurrent.*;

/**
 * Created by LiuDongguang on 2017/10/9.
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Future<Integer> submit = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 999;
            }
        });
        System.out.println("111111111");
        System.out.println(submit.get());
        System.out.println("2222222222222");
    }
}
