package org.rule.process.scheduling;

import org.rule.process.scheduling.algorithm.FCFS;
import org.rule.process.scheduling.algorithm.RelativePriority;
import org.rule.process.scheduling.algorithm.SPF;
import org.rule.process.scheduling.bll.ProcessBLL;
import org.rule.process.scheduling.model.ProcessFCFSModel;
import org.rule.process.scheduling.model.ProcessRFPFModel;
import org.rule.process.scheduling.model.ProcessSPFModel;
import org.rule.process.scheduling.tools.StringTools;

public class TestSchedule {

    public static void main(String[] args) {
        TestSchedule schedule = new TestSchedule();

        schedule.testFCFS();
        System.out.println("---------------------------------------------------------------------------------");
        schedule.testSPF();
        System.out.println("---------------------------------------------------------------------------------");
        schedule.testRFPF();
    }

    private void testFCFS() {

        FCFS fcfs = new FCFS();
        ProcessFCFSModel[] processArray = ProcessBLL.getFCFSModelArray();
        int timeSum = fcfs.execute(processArray);
        System.out.println("FCFS总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t开始时间\t完成时间\t周转时间\t带权周转时间\t");
        for (ProcessFCFSModel process : processArray) {
            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getStartRunTime() + "\t" + process.getFinishTime() + "\t" +
                    process.getTurnaroundTime() + "\t" + StringTools.format(process.getTurnaroundWeightTime()));
        }
    }

    private void testSPF() {

        ProcessSPFModel[] processArray = ProcessBLL.getSPFModelArray();
        SPF spf = new SPF();
        int timeSum = spf.execute(processArray);
        System.out.println("SPF总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t开始时间\t完成时间\t周转时间\t带权周转时间\t");
        for (ProcessSPFModel process : processArray) {
            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getStartRunTime() + "\t" + process.getFinishTime() + "\t" +
                    process.getTurnaroundTime() + "\t" + StringTools.format(process.getTurnaroundWeightTime()));
        }
    }

    private void testRFPF() {
        ProcessRFPFModel[] processArray = ProcessBLL.getRFPFModelArray();
        RelativePriority relativePriority = new RelativePriority();
        int timeSum = relativePriority.execute(processArray);
        System.out.println("RFPF总运行时间：" + timeSum + "(ms)");
        System.out.println("进程调度过程：\n进程名\t到达时间\t服务时间\t优先级\t开始时间\t完成时间\t周转时间\t带权周转时间\t");
        for (ProcessRFPFModel process : processArray) {
            System.out.println(process.getProcessId() + "\t" +
                    process.getComingTime() + "\t" + process.getRunTime() + "\t" +
                    process.getPriority() + "\t" +
                    process.getStartRunTime() + "\t" + process.getFinishTime() + "\t" +
                    process.getTurnaroundTime() + "\t" + StringTools.format(process.getTurnaroundWeightTime()));
        }
    }
}
