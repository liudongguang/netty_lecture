package com.ldg.a20170912.listener.watcherImpl;

import com.ldg.a20170912.listener.api.Watcher;

public class Police implements Watcher
{  
     @Override  
     public void update()  
     {  
          System.out.println("运输车有行动，警察护航");
     }  
}  