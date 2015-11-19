package org.rule.process.scheduling.bll;

import org.rule.process.scheduling.model.ProcessFCFSModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法帮助类
 * Created by Naga on 2015/11/19.
 */
public class ProcessBLL {

    public static List<ProcessFCFSModel> getFCFSModelList() {

        List<ProcessFCFSModel> models = new ArrayList<>();
        models.add(new ProcessFCFSModel("A", 1, 0));
        models.add(new ProcessFCFSModel("B", 100, 1));
        models.add(new ProcessFCFSModel("C", 1, 2));
        models.add(new ProcessFCFSModel("D", 100, 3));

        return models;
    }

    public static ProcessFCFSModel[] getFCFSModelArray() {

        ProcessFCFSModel[] models = new ProcessFCFSModel[4];
        models[0] = new ProcessFCFSModel("A", 1, 0);
        models[1] = new ProcessFCFSModel("B", 100, 1);
        models[2] = new ProcessFCFSModel("C", 1, 2);
        models[3] = new ProcessFCFSModel("D", 100, 3);

        return models;
    }

}
