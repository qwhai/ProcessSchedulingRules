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
            System.out.println(TAG + ">数据为空");
            return -1;
        }

        if (!(processList instanceof ProcessAFPFModel[])) {
            System.out.println(TAG + ">数据类型出错");
            return -2;
        }

        ProcessAFPFModel[] processes = (ProcessAFPFModel[]) processList;
        boolean[] runFlag = new boolean[processes.length]; // 记录所有进程是否运行结束
        long[] comingTime = new long[processes.length];
        // TODO
        recordComingTime(processes, comingTime);
        ProcessAFPFModel currentProcess;
        long currentTime = 0;
        while(!ProcessBLL.noProcessWaitting(runFlag)) {
            //

            currentTime++;
        }

        return 0;
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
