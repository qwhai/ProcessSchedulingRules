package org.rule.process.scheduling.bll;

import org.rule.process.scheduling.model.ProcessFCFSModel;
import org.rule.process.scheduling.model.ProcessModel;
import org.rule.process.scheduling.model.ProcessRFPFModel;
import org.rule.process.scheduling.model.ProcessSPFModel;

/**
 * 算法帮助类
 * Created by Naga on 2015/11/19.
 */
public class ProcessBLL {

    public static ProcessFCFSModel[] getFCFSModelArray() {

        ProcessFCFSModel[] models = new ProcessFCFSModel[5];

        models[0] = new ProcessFCFSModel("A", 4, 0);
        models[1] = new ProcessFCFSModel("B", 3, 1);
        models[2] = new ProcessFCFSModel("C", 5, 2);
        models[3] = new ProcessFCFSModel("D", 2, 3);
        models[4] = new ProcessFCFSModel("E", 4, 4);

        return models;
    }

    public static ProcessSPFModel[] getSPFModelArray() {

        ProcessSPFModel[] models = new ProcessSPFModel[5];

        models[0] = new ProcessSPFModel("A", 4, 0);
        models[1] = new ProcessSPFModel("B", 3, 1);
        models[2] = new ProcessSPFModel("C", 5, 2);
        models[3] = new ProcessSPFModel("D", 2, 3);
        models[4] = new ProcessSPFModel("E", 4, 4);

        return models;
    }

    public static ProcessRFPFModel[] getRFPFModelArray() {

        ProcessRFPFModel[] models = new ProcessRFPFModel[6];

        models[0] = new ProcessRFPFModel("A", 4, 0, 1);
        models[1] = new ProcessRFPFModel("B", 4, 1, 2);
        models[2] = new ProcessRFPFModel("C", 4, 3, 3);
        models[3] = new ProcessRFPFModel("D", 1, 5, 4);
        models[4] = new ProcessRFPFModel("E", 2, 14, 3);
        models[5] = new ProcessRFPFModel("F", 3, 15, 4);

        return models;
    }

    /**
     * 判断所有进行是否都已经运行完毕
     * @param runFlag
     *          进程队列中的进程运行情况
     * @return
     *          是否全部执行结束
     */
    public static boolean noProcessWaitting(boolean[] runFlag) {
        for (boolean flag : runFlag) {
            if (!flag) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断一个进程是否已经到达
     * @param process
     *          进程
     * @param currentTime
     *          当前时间
     * @return
     *          是否到达
     */
    public static boolean isProcessComing(ProcessModel process, long currentTime) {
        if (process instanceof ProcessRFPFModel) {
            return ((ProcessRFPFModel)process).getComingTime() <= currentTime;
        }

        if (process instanceof ProcessFCFSModel) {
            return ((ProcessFCFSModel)process).getComingTime() <= currentTime;
        }

        if (process instanceof ProcessSPFModel) {
            return ((ProcessSPFModel)process).getComingTime() <= currentTime;
        }

        return false;
    }
}
