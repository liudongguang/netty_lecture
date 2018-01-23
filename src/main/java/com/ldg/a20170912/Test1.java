package com.ldg.a20170912;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * Created by LiuDongguang on 2017/9/12.
 */
public class Test1 {
    public static void main(String[] args) {
        int rs=Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(rs);
    }
}
