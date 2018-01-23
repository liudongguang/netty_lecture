package com.ldg.a20170912.listener;

import com.ldg.a20170912.listener.watchedImpl.Transporter;
import com.ldg.a20170912.listener.watcherImpl.Police;
import com.ldg.a20170912.listener.watcherImpl.Security;
import com.ldg.a20170912.listener.watcherImpl.Thief;

public class Test {
    public static void main(String[] args) {
        Transporter transporter = new Transporter();
        Police police = new Police();
        Security security = new Security();
        Thief thief = new Thief();
        transporter.addWatcher(police);
        transporter.addWatcher(security);
        transporter.addWatcher(thief);
        transporter.notifyWatchers();
    }
}  