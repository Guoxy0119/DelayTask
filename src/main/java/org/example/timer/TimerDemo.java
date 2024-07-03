package org.example.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer内部有一个TaskQueue对象，用来保存TimerTask任务的，会根据nextExecutionTime来排序，保证能够快速获取到最早需要被执行的延迟任务。
 * 在Timer内部还有一个执行任务的线程TimerThread，这个线程就跟DelayQueue demo中开启的线程作用是一样的，用来执行到了延迟时间的任务。
 * 所以总的来看，Timer有点像整体封装了DelayQueue demo中的所有东西，让用起来简单点。
 * <p>
 * 虽然Timer用起来比较简单，但是在阿里规范中是不推荐使用的，主要是有以下几点原因：
 * 1. Timer使用单线程来处理任务，长时间运行的任务会导致其他任务的延时处理
 * 2. Timer没有对运行时异常进行处理，一旦某个任务触发运行时异常，会导致整个Timer崩溃，不安全
 */
public class TimerDemo {

    public static void main(String[] args) {

        Timer timer = new Timer();

        System.out.println("提交延迟任务");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行延迟任务");
            }
        }, 5000);


    }

}
