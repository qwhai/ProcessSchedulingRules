package org.rule.process.scheduling;

import org.rule.process.scheduling.algorithm.AFPF;
import org.rule.process.scheduling.algorithm.FCFS;
import org.rule.process.scheduling.algorithm.RFPF;
import org.rule.process.scheduling.algorithm.SPF;
import org.rule.process.scheduling.bll.ProcessBLL;
import org.rule.process.scheduling.model.ProcessAFPFModel;
import org.rule.process.scheduling.model.ProcessFCFSModel;
import org.rule.process.scheduling.model.ProcessRFPFModel;
import org.rule.process.scheduling.model.ProcessSPFModel;
import org.rule.process.scheduling.tools.StringTools;

/**
 * 测试类
 * Created by Naga on 2015/11/19.
 * Blog : http://blog.csdn.net/lemon_tree12138
 */
public class TestSchedule {

    public static void main(String[] args) {
        TestSchedule schedule = new TestSchedule();

        schedule.testFCFS();
        System.out.println("---------------------------------------------------------------------------------");
        schedule.testSPF();
        System.out.println("---------------------------------------------------------------------------------");
        schedule.testRFPF();
        System.out.println("---------------------------------------------------------------------------------");
        schedule.testAFPF();
    }

    private void testFCFS() {
        FCFS fcfs = new FCFS();
        ProcessFCFSModel[] processArray = ProcessBLL.getFCFSModelArray();
        int timeSum = fcfs.execute(processArray);
        int turnaroundSum = 0;
        double turnaroundWeightSum = 0;
        System.out.println("FCFS总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t开始时间\t完成时间\t周转时间\t带权周转时间");
        for (ProcessFCFSModel process : processArray) {
            turnaroundSum += process.getTurnaroundTime();
            turnaroundWeightSum += process.getTurnaroundWeightTime();

            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getStartRunTime() + "\t" + process.getFinishTime() + "\t" +
                    process.getTurnaroundTime() + "\t" + StringTools.format(process.getTurnaroundWeightTime()));
        }

        System.out.println("平均周转时间：" + StringTools.format(turnaroundSum / processArray.length));
        System.out.println("平均带权周转时间：" + StringTools.format(turnaroundWeightSum / processArray.length));
    }

    private void testSPF() {
        ProcessSPFModel[] processArray = ProcessBLL.getSPFModelArray();
        SPF spf = new SPF();
        int timeSum = spf.execute(processArray);
        int turnaroundSum = 0;
        double turnaroundWeightSum = 0;
        System.out.println("SPF总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t开始时间\t完成时间\t周转时间\t带权周转时间");
        for (ProcessSPFModel process : processArray) {
            turnaroundSum += process.getTurnaroundTime();
            turnaroundWeightSum += process.getTurnaroundWeightTime();

            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getStartRunTime() + "\t" + process.getFinishTime() + "\t" +
                    process.getTurnaroundTime() + "\t" + StringTools.format(process.getTurnaroundWeightTime()));
        }

        System.out.println("平均周转时间：" + StringTools.format(turnaroundSum / processArray.length));
        System.out.println("平均带权周转时间：" + StringTools.format(turnaroundWeightSum / processArray.length));
    }

    private void testRFPF() {
        ProcessRFPFModel[] processArray = ProcessBLL.getRFPFModelArray();
        RFPF rfpf = new RFPF();
        int timeSum = rfpf.execute(processArray);
        int turnaroundSum = 0;
        double turnaroundWeightSum = 0;
        System.out.println("RFPF总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t优先级\t开始时间\t完成时间\t周转时间\t带权周转时间");
        for (ProcessRFPFModel process : processArray) {
            turnaroundSum += process.getTurnaroundTime();
            turnaroundWeightSum += process.getTurnaroundWeightTime();

            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getPriority() + "\t" +
                    process.getStartRunTime() + "\t" + process.getFinishTime() + "\t" +
                    process.getTurnaroundTime() + "\t" + StringTools.format(process.getTurnaroundWeightTime()));
        }

        System.out.println("平均周转时间：" + StringTools.format(turnaroundSum / processArray.length));
        System.out.println("平均带权周转时间：" + StringTools.format(turnaroundWeightSum / processArray.length));
    }

    private void testAFPF() {
        ProcessAFPFModel[] processArray = ProcessBLL.getAFPFModelArray();
        AFPF afpf = new AFPF();
        int timeSum = afpf.execute(processArray);
        int turnaroundSum = 0;
        double turnaroundWeightSum = 0;
        System.out.println("AFPF总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t优先级\t开始时间\t完成时间\t周转时间\t带权周转时间\t剩余时间");
        for (ProcessAFPFModel process : processArray) {
            turnaroundSum += process.getTurnaroundTime();
            turnaroundWeightSum += process.getTurnaroundWeightTime();

            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getPriority() + "\t" + process.getStartRunTime() + "\t" +
                    process.getFinishTime() + "\t" + process.getTurnaroundTime() + "\t" +
                    StringTools.format(process.getTurnaroundWeightTime()) + "\t" + process.getFreeTime());
        }

        System.out.println("平均周转时间：" + StringTools.format(turnaroundSum / processArray.length));
        System.out.println("平均带权周转时间：" + StringTools.format(turnaroundWeightSum / processArray.length));
    }
}
