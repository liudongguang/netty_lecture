package com.ldg.a20170912.listener.watcherImpl;

import com.ldg.a20170912.listener.api.Watcher;

public class Security implements Watcher {
    @Override
    public void update() {
        System.out.println("运输车有行动，保安贴身保护");
    }
} 