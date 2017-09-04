package com.ldg.a20170912;

import java.util.concurrent.*;

/**
 * Created by LiuDongguang on 2017/10/9.
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        FutureTask<String> future=new FutureTask<String>(new Callable() {
            @Override
            public String call() throws Exception {
                int j=1;
                for(int i=0;i<10000000;i++){
                    j+=i;
                }
                return "aaaaaaaaaaa";
            }
        });
        executorService.execute(future);
        //boolean cancelState=future.cancel(false);
        //System.out.println("2222222222222"+cancelState);
        System.out.println("111111111");
        System.out.println(future.get(2,TimeUnit.SECONDS));
        System.out.println("2222222222222");
    }
}
