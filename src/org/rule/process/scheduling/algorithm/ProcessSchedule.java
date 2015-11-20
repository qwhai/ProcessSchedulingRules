package org.rule.process.scheduling.algorithm;

import org.rule.process.scheduling.model.ProcessModel;

/**
 * 进程调度接口
 * 其他的进程调度算法类都是要来实现这个接口
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public interface ProcessSchedule {

    int execute(ProcessModel ... processList);
}
