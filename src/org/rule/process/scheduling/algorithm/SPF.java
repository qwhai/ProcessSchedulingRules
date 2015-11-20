package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.bll.ProcessBLL;
import org.rule.process.scheduling.model.ProcessModel;
import org.rule.process.scheduling.model.ProcessSPFModel;

/**
 * 短进程优先
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class SPF implements ProcessSchedule {

    private static final String TAG = SPF.class.getSimpleName();

    @Override
    public int execute(ProcessModel... processList) {
        if (processList == null || processList.length == 0) {
            System.out.println(TAG + ">数据为空");
            return -1;
        }

        if (!(processList instanceof ProcessSPFModel[])) {
            System.out.println(TAG + ">数据类型出错");
            return -2;
        }

        ProcessSPFModel[] processArray = (ProcessSPFModel[])processList;
        boolean[]runFlag = new boolean[processArray.length];
        int runTimeSum = 0;
        int index = 0;
        ProcessSPFModel currentProcess = processArray[index];
        while(!ProcessBLL.noProcessWaitting(runFlag)) {
            currentProcess.setStartRunTime(runTimeSum);
            if (runTimeSum < currentProcess.getComingTime()) {
                runTimeSum = (int)currentProcess.getComingTime();
            }

            runTimeSum += currentProcess.getRunTime();
            currentProcess.setFinishTime(runTimeSum);
            currentProcess.setTurnaroundTime(runTimeSum - currentProcess.getComingTime());
            currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getRunTime());

            runFlag[index] = true;

            index = getIndexMinRuntime(processArray, runFlag, runTimeSum);
            if (0 <= index && index < processArray.length) {
                currentProcess = processArray[index];
            } else {
                System.out.println("未知异常");
                break;
            }
        }

        return runTimeSum;
    }

    /**
     * 获得进程列表中服务时间最短的进程
     * @param processArray
     *          进程列表
     * @param runFlag
     *          进程运行标志位
     * @return
     *          进程下标
     */
    private int getIndexMinRuntime(ProcessSPFModel[] processArray, boolean[] runFlag, int runTimeSum) {
        if (processArray.length == 0 || runFlag.length != processArray.length) {
            return -1;
        }

        int earliestIndex = 0; // 未执行的最早的进程
        long earliestTime = Long.MAX_VALUE;
        int index = -1;
        long minTime = Long.MAX_VALUE;
        for (int i = 0; i < processArray.length; i++) {
            if (runFlag[i]) {
                continue;
            }

            if (processArray[i].getComingTime() < earliestTime) {
                earliestIndex = i;
                earliestTime = processArray[i].getComingTime();
            }

            if (processArray[i].getComingTime() > runTimeSum) {
                continue;
            }

            if (processArray[i].getRunTime() < minTime) {
                minTime = processArray[i].getRunTime();
                index = i;
            }
        }

        index = index < 0 ? earliestIndex : index;

        return index;
    }
}
