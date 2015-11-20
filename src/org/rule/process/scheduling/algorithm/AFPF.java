package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.bll.ProcessBLL;
import org.rule.process.scheduling.model.ProcessAFPFModel;
import org.rule.process.scheduling.model.ProcessModel;
import org.rule.process.scheduling.sort.QKSort;

/**
 * 抢占式优先权调度算法(绝对优先权)
 * 本算法可以从时间的角度来模拟
 * 即随着时间的推移，进程逐个被完成.
 * 当有一个优先更高的进程进来时，当前进程被挂起
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class AFPF implements ProcessSchedule {

    private static final String TAG = AFPF.class.getSimpleName();

    @Override
    public int execute(ProcessModel... processList) {
        if (processList == null || processList.length == 0) {
            System.err.println(TAG + ">数据为空");
            return -1;
        }

        if (!(processList instanceof ProcessAFPFModel[])) {
            System.err.println(TAG + ">数据类型出错");
            return -2;
        }

        ProcessAFPFModel[] processes = (ProcessAFPFModel[]) processList;
        long[] comingTime = new long[processes.length];
        recordComingTime(processes, comingTime);
        ProcessAFPFModel currentProcess = null; // 占用CPU的进程
        int index = -1;
        long currentTime = 0; // 当前时间
        while(!isDone(processes)) {
            index = getNextIndex(processes, currentTime);
            if (index > processes.length || index < 0) {
                System.err.println(TAG + ">未知异常");
                break;
            }

            // 针对尚未初始化和已经运行结束的进程
            if (null == currentProcess || currentProcess.getFreeTime() <= 0) {
                currentProcess = processes[index];
            }

            // 针对有抢占进程时
            if (!processes[index].getProcessId().equals(currentProcess.getProcessId())) {
                currentProcess = processes[index];
            }

            // 针对中间CPU空闲的逻辑处理
            if (currentTime < currentProcess.getComingTime()) {
                currentTime = currentProcess.getComingTime();
            }

            { // 进程被执行过程模拟
                currentProcess.reduceSelfFreeTime();
                currentTime++;
                if (currentProcess.getFreeTime() <= 0) {
                    currentProcess.setFinishTime(currentTime);
                    currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getComingTime());
                    currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getRunTime());
                }
            }
        }

        return (int)currentTime;
    }

    /**
     * 获得将在下一个时间单位获得CPU的进程下标
     *
     * @param processAarry
     *          进程列表
     * @param currentTime
     *          当前时间
     * @return
     *          进程下标
     */
    private int getNextIndex(ProcessAFPFModel[] processAarry, long currentTime) {
        int index = -1;
        if (processAarry == null || processAarry.length == 0) {
            return index;
        }

        int maxPriority = Integer.MIN_VALUE;
        int earliestIndex = -1; // 未执行的最早的进程
        long earliestTime = Long.MAX_VALUE;
        int earliestMaxPriority = Integer.MIN_VALUE;
        for (int i = 0; i < processAarry.length; i++) {
            if (processAarry[i].getFreeTime() <= 0) {
                continue;
            }

            // 过滤尚未到达的进程
            if (!ProcessBLL.isProcessComing(processAarry[i], currentTime)) {
                if (earliestTime > processAarry[i].getComingTime()) {
                    earliestIndex = i;
                    earliestTime = processAarry[i].getComingTime();
                    earliestMaxPriority = processAarry[i].getPriority();
                } else if (earliestTime == processAarry[i].getComingTime()) {
                    if (earliestMaxPriority < processAarry[i].getPriority()) {
                        earliestMaxPriority = processAarry[i].getPriority();
                        earliestIndex = i;
                    }
                }
                continue;
            }

            if (maxPriority < processAarry[i].getPriority()) {
                maxPriority = processAarry[i].getPriority();
                index = i;
            }
        }

        index = index < 0 ? earliestIndex : index;

        return index;
    }

    /**
     * 是否所有的进程都执行完毕
     *
     * @param array
     *          进程列表
     * @return
     *          是否执行结束
     */
    private boolean isDone(ProcessAFPFModel[] array) {
        for (ProcessAFPFModel process : array) {
            if (process.getFreeTime() > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 记录下当前进程的到达时间
     *
     * @param array
     *          进程队列
     * @param times
     *          进程到达时间列表
     */
    private void recordComingTime(ProcessAFPFModel[] array, long[] times) {
        if (times == null || times.length == 0) {
            times = new long[array.length];
        }

        for (int i = 0; i < array.length; i++) {
            times[i] = array[i].getComingTime();
        }

        QKSort.quickSort(times);
    }
}
