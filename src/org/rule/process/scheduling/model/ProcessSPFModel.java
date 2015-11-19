package org.rule.process.scheduling.model;

/**
 * Created by Naga on 2015/11/19.
 */
public class ProcessSPFModel extends ProcessModel {

    private long comingTime; // 到达时间
    private long startRunTime; // 开始执行时间
    private long finishTime; // 完成时间
    private long turnaroundTime; // 周转时间
    private double turnaroundWeightTime; // 带权周转时间

    public ProcessSPFModel(String processId, long runTime, long comingTime) {
        this.processId = processId;
        this.runTime = runTime;
        this.comingTime = comingTime;
    }

    public long getComingTime() {
        return comingTime;
    }

    public void setComingTime(long comingTime) {
        this.comingTime = comingTime;
    }

    public long getStartRunTime() {
        return startRunTime;
    }

    public void setStartRunTime(long startRunTime) {
        this.startRunTime = startRunTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public long getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(long turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public double getTurnaroundWeightTime() {
        return turnaroundWeightTime;
    }

    public void setTurnaroundWeightTime(double turnaroundWeightTime) {
        this.turnaroundWeightTime = turnaroundWeightTime;
    }
}
