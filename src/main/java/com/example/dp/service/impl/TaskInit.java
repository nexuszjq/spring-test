package com.example.dp.service.impl;

import com.example.dp.entity.Task;
import com.example.dp.enums.ActionType;
import com.example.dp.service.State;

/**
 * desc
 *
 * @author nexus 2022/03/13 21:33
 */
public class TaskInit implements State {
    @Override
    public void update(Task task, ActionType actionType) {
        if  (actionType == ActionType.START) {
            TaskOngoing taskOngoing = new TaskOngoing();
            taskOngoing.add(new ActivityObserver());
            taskOngoing.add(new TaskManageObserver());
            task.setState(taskOngoing);
        }
    }
}