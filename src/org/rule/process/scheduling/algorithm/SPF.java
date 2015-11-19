package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.model.ProcessModel;
import org.rule.process.scheduling.model.ProcessSPFModel;

/**
 * 短进程优先
 * Created by Naga on 2015/11/19.
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
        while(!noProcessWaitting(runFlag)) {
            currentProcess.setStartRunTime(runTimeSum);
            runTimeSum += currentProcess.getRunTime();
            currentProcess.setFinishTime(runTimeSum);
            currentProcess.setTurnaroundTime(runTimeSum - currentProcess.getComingTime());
            currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getRunTime());

            runFlag[index] = true;

            index = getIndexMinRuntime(processArray, runFlag, runTimeSum);
            if (index >= 0) {
                currentProcess = processArray[index];
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

        int index = 0;
        long minTime = Long.MAX_VALUE;
        for (int i = 0; i < processArray.length; i++) {
            if (runFlag[i]) {
                continue;
            }

            if (processArray[i].getComingTime() > runTimeSum) {
                continue;
            }

            if (processArray[i].getRunTime() < minTime) {
                minTime = processArray[i].getRunTime();
                index = i;
            }
        }

        return index;
    }

    /**
     * 判断所有进行是否都已经运行完毕
     * @param runFlag
     * @return
     */
    private boolean noProcessWaitting(boolean[] runFlag) {
        for (boolean flag : runFlag) {
            if (!flag) {
                return false;
            }
        }

        return true;
    }
}
