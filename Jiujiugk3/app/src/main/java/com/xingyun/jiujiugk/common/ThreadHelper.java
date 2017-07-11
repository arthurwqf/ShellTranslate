package com.xingyun.jiujiugk.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangqf on 2017/2/23 0023.
 */

public class ThreadHelper {
    private static ExecutorService threadPool;

    static {
        threadPool = Executors.newFixedThreadPool(5);
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
