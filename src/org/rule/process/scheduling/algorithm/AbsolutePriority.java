package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.model.ProcessModel;

/**
 * 抢占式优先权调度算法(绝对优先权)
 * Created by Naga on 2015/11/19.
 */
public class AbsolutePriority implements ProcessSchedule {

    @Override
    public int execute(ProcessModel... processList) {
        return 0;
    }
}
