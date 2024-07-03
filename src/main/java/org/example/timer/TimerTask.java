package org.example.timer;

public abstract class TimerTask implements Runnable{

    //忽略其他属性


    /**
     * 代表下一次任务执行的时间，在提交任务的时候会计算出nextExecutionTime值
     */
    Long nextExecutionTime;

}
