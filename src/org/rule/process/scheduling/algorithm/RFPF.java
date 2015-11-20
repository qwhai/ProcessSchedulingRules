package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.bll.ProcessBLL;
import org.rule.process.scheduling.model.ProcessModel;
import org.rule.process.scheduling.model.ProcessRFPFModel;

/**
 * 非抢占式优先权算法(相对优先权)
 * RFPF
 * Created by Naga on 2015/11/19.
 */
public class RFPF implements ProcessSchedule {

    private static final String TAG = RFPF.class.getSimpleName();

    @Override
    public int execute(ProcessModel... processList) {
        if (processList == null || processList.length == 0) {
            System.out.println(TAG + ">数据为空");
            return -1;
        }

        if (!(processList instanceof ProcessRFPFModel[])) {
            System.out.println(TAG + ">数据类型出错");
            return -2;
        }

        ProcessRFPFModel[] processes = (ProcessRFPFModel[])processList;
        boolean[] runFlag = new boolean[processes.length];
        int currentTime = 0;
        int index = -1;
        ProcessRFPFModel currentProcess;
        for (int i = 0; i < processes.length; i++) {
            index = getIndexComingMaxPriority(processes, runFlag, currentTime);
            if (0 <= index && index <= processes.length) {
                currentProcess = processes[index];
                currentProcess.setStartRunTime(currentTime);
                currentTime += currentProcess.getRunTime();
                currentProcess.setFinishTime(currentTime);
                currentProcess.setTurnaroundTime(currentTime - currentProcess.getComingTime());
                currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getRunTime());

                runFlag[index] = true;
            } else {
                System.out.println("未知异常");
                break;
            }
        }

        return currentTime;
    }

    /**
     * 获得队列中已经到达、尚未执行且优先权最大的进程下标
     * 如果在尚未执行的进程队列中，所有的都未到达，就取最先到达的
     *
     * @param processes
     *          进程队列
     * @param runFlag
     *          进程执行标志
     * @param currentTime
     *          当前时间
     * @return
     *          进程下标
     */
    private int getIndexComingMaxPriority(ProcessRFPFModel[] processes, boolean[] runFlag, int currentTime) {
        int maxPriority = Integer.MIN_VALUE;
        int maxPriorityIndex = -1;
        int earliestIndex = -1; // 未执行的最早的进程
        long earliestTime = Long.MAX_VALUE;
        ProcessRFPFModel currentProcess;
        for (int i = 0; i < processes.length; i++) {
            if (runFlag[i]) {
                continue;
            }

            currentProcess = processes[i];
            if (!ProcessBLL.isProcessComing(currentProcess, currentTime)) {
                if (earliestTime > currentProcess.getComingTime()) {
                    earliestIndex = i;
                    earliestTime = currentProcess.getComingTime();
                }
                continue;
            }

            if (currentProcess.getPriority() > maxPriority) {
                maxPriority = currentProcess.getPriority();
                maxPriorityIndex = i;
            }
        }

        maxPriorityIndex = maxPriorityIndex < 0 ? earliestIndex : maxPriorityIndex;

        return maxPriorityIndex;
    }
}
