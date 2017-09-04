package com.ldg.a20170912;

import com.ldg.a20170912.listener.watchedImpl.Transporter;
import com.ldg.a20170912.listener.watcherImpl.Police;
import com.ldg.a20170912.listener.watcherImpl.Security;
import com.ldg.a20170912.listener.watcherImpl.Thief;

import java.util.concurrent.*;

/**
 * Created by LiuDongguang on 2017/10/9.
 */
public class FutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Transporter transporter = new Transporter();
        Police police = new Police();
        Security security = new Security();
        Thief thief = new Thief();
        transporter.addWatcher(police);
        transporter.addWatcher(security);
        transporter.addWatcher(thief);
        Future<Void> submit = executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(5000);
                transporter.notifyWatchers();
                return null;
            }
        });

    }
}
