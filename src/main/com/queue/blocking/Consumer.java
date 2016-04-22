package com.queue.blocking;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Title: Consumer
 * @Description:
 * @author: xian jie
 * @date: 2016/1/26 11:48
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class Consumer implements Runnable {

    private String name;
    private BlockingQueue<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Consumer(String name, BlockingQueue<String> queue) {
        this.name = name;
        this.queue = queue;
    }

    public void run() {
        System.out.println("启动消费者线程！" + name);
        Random r = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println(name + "正从队列获取数据...");
                String data = queue.poll(5, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println(name + "消费者拿到数据：" + data);
                    System.out.println(name + "消费者正在消费数据：" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
                    // 超过5s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(name + "退出消费者线程！");
        }
    }
}

