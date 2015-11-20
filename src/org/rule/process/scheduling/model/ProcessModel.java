package org.rule.process.scheduling.model;

/**
 * 单个进程对象
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class ProcessModel {

    String processId; // 进程标识

    long runTime; // 进程完整执行预计的时间



    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

}
