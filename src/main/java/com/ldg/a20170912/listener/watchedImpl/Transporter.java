package com.ldg.a20170912.listener.watchedImpl;

import com.ldg.a20170912.listener.api.Watched;
import com.ldg.a20170912.listener.api.Watcher;

import java.util.ArrayList;
import java.util.List;

public class Transporter implements Watched {
    private List<Watcher> list = new ArrayList<Watcher>();

    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers() {
        for (Watcher watcher : list) {
            watcher.update();
        }
    }
}  