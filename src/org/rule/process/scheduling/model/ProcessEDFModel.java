package org.rule.process.scheduling.model;

/**
 * 模拟最早截止时间优先即EDF(Earliest Deadline First)算法对象
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class ProcessEDFModel extends ProcessModel {

    private long startTime; // 进程开始截止时间

    private long deadline; // 进程结束截止时间

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}
