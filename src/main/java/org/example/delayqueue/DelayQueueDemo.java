package org.example.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * 开启一个线程从DelayQueue中获取任务，然后提交了三个任务，延迟时间分为别5s，3s，8s。
 * offer方法在提交任务的时候，会通过根据compareTo的实现对任务进行排序，将最先需要被执行的任务放到队列头。
 * take方法获取任务的时候，会拿到队列头部的元素，也就是队列中最早需要被执行的任务，通过getDelay返回值判断任务是否需要被立刻执行，如果需要的话，就返回任务，如果不需要就会等待这个任务到延迟时间的剩余时间，当时间到了就会将任务返回。
 */
public class DelayQueueDemo {

    public static void main(String[] args) {

        DelayQueue<Order> queue = new DelayQueue<>();

        new Thread(() -> {
            while (true) {
                try {
                    Order order = queue.take();
                    System.out.println("获取到延迟任务:{" + order.getTaskContent() + "}");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }).start();

        System.out.println("提交延迟任务");
        queue.offer(new Order(5L, "5s延迟任务"));
        queue.offer(new Order(3L, "3s延迟任务"));
        queue.offer(new Order(8L, "8s延迟任务"));

    }

}
