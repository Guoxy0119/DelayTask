package org.example.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue是一个BlockingQueue（无界阻塞）队列，它本质就是封装了一个PriorityQueue（优先队列），PriorityQueue内部使用完全二叉堆（不知道的自行了解哈）来实现队列元素排序，
 * 我们在向DelayQueue队列中添加元素时，会给元素一个Delay（延迟时间）作为排序条件，队列中最小的元素会优先放在队首。
 * 队列中的元素只有到了Delay时间才允许从队列中取出。队列中可以放基本数据类型或自定义实体类，在存放基本数据类型时，
 * 优先队列中元素默认升序排列，自定义实体类就需要我们根据类属性值比较计算了。
 * <p>
 * 要实现DelayQueue延时队列，队中元素要implements Delayed 接口，这个接口里只有一个getDelay方法，用于设置延期时间。
 * Order类中compareTo方法负责对队列中的元素进行排序。
 */

public class Order implements Delayed {

    /**
     * 延迟时间
     */
    private final Long triggerTime;
    /**
     * 延迟任务的具体内容
     */
    private final String taskContent;

    /**
     * getDelay方法返回这个任务还剩多久时间可以执行，小于0的时候说明可以这个延迟任务到了执行的时间了。
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * compareTo这个是对任务排序的，保证最先到延迟时间的任务排到队列的头。
     */
    @Override
    public int compareTo(Delayed o) {
        return this.triggerTime.compareTo(((Order) o).triggerTime);
    }


    public Order(long triggerTime, String taskContent) {
        this.triggerTime = System.currentTimeMillis() + triggerTime * 1000;
        this.taskContent = taskContent;
    }

    public Order(long triggerTime, String taskContent, TimeUnit unit) {
        this.triggerTime = System.currentTimeMillis() + (triggerTime > 0 ? unit.toMillis(triggerTime) : 0);
        this.taskContent = taskContent;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public String getTaskContent() {
        return taskContent;
    }
}
