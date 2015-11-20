package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.model.ProcessFCFSModel;
import org.rule.process.scheduling.model.ProcessModel;

/**
 * 先来先服务算法
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class FCFS implements ProcessSchedule {

    private static final String TAG = FCFS.class.getSimpleName();

    @Override
    public int execute(ProcessModel... processList) {
        if (processList == null || processList.length == 0) {
            System.out.println(TAG + ">数据为空");
            return -1;
        }

        if (!(processList instanceof ProcessFCFSModel[])) {
            System.out.println(TAG + ">数据类型出错");
            return -2;
        }

        ProcessFCFSModel[] fcfsModels = (ProcessFCFSModel[])processList;
        int runTimeSum = 0;
        for (ProcessFCFSModel model : fcfsModels) {
            if (runTimeSum < model.getComingTime()) {
                runTimeSum = (int)model.getComingTime();
            }

            model.setStartRunTime(runTimeSum);
            runTimeSum += model.getRunTime();
            model.setFinishTime(runTimeSum);
            model.setTurnaroundTime(runTimeSum - model.getComingTime());
            model.setTurnaroundWeightTime(1.0 * model.getTurnaroundTime() / model.getRunTime());
        }

        return runTimeSum;
    }
}
