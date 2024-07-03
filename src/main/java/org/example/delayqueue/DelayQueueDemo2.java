package org.example.delayqueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue的put方法是线程安全的，因为put方法内部使用了ReentrantLock锁进行线程同步。
 * DelayQueue还提供了两种出队的方法 poll() 和 take() ， poll() 为非阻塞获取，没有到期的元素直接返回null；
 * take() 阻塞方式获取，没有到期的元素线程将会等待。
 */
public class DelayQueueDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Order order1 = new Order(5, "order1", TimeUnit.SECONDS);
        Order order2 = new Order(10, "order2", TimeUnit.SECONDS);
        Order order3 = new Order(15, "order3", TimeUnit.SECONDS);

        DelayQueue<Order> queue = new DelayQueue<>();
        queue.put(order1);
        queue.put(order2);
        queue.put(order3);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        while (!queue.isEmpty()) {
            /**
             * 取队列头部元素是否过期
             */
            Order task = queue.poll();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.getTaskContent(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(1000);

        }

    }
}
