package com.queue.blocking;

import java.util.concurrent.*;

/**
 * @Title: BlockingQueueTest
 * @Description:
 * @author: xian jie
 * @date: 2016/1/26 11:49
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class BlockingQueueTest {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        //SynchronousQueue<String> 只有有人来拿的时候数据才能放的进去
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

        Producer producer1 = new Producer("rain", queue);
        Producer producer2 = new Producer("tom", queue);
        Producer producer3 = new Producer("jack", queue);
        Consumer consumer1 = new Consumer("消费者1", queue);
        Consumer consumer2 = new Consumer("消费者2", queue);
        Consumer consumer3 = new Consumer("消费者3", queue);

        // 借助Executors 线程池
        ExecutorService service = ConsumerProducerExecutors.getService();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        // 执行3s
        TimeUnit.SECONDS.sleep(3);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        // 退出Executor
        service.shutdown();
    }
}
