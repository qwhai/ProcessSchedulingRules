package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.model.ProcessModel;

/**
 * 非抢占式优先权算法(相对优先权)
 * Created by Naga on 2015/11/19.
 */
public class RelativePriority implements ProcessSchedule {

    @Override
    public int execute(ProcessModel... processList) {
        return 0;
    }
}
