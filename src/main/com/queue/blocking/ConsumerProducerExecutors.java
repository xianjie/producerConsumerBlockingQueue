package com.queue.blocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: ConsumerProducerExecutors
 * @Description:
 * @author: xian jie
 * @date: 2016/2/24 11:39
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class ConsumerProducerExecutors {

    private ConsumerProducerExecutors() {

    }

    private static ExecutorService service;

    static {
        service = Executors.newCachedThreadPool();
    }

    public static ExecutorService getService() {
        return service;
    }
}
