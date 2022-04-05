package com.example.dp.service.impl;

import com.example.dp.entity.Task;
import com.example.dp.enums.ActionType;
import com.example.dp.service.State;

/**
 * desc
 *
 * @author nexus 2022/03/13 21:36
 */
public class TaskPaused implements State {
    @Override
    public void update(Task task, ActionType actionType) {
        if (actionType == ActionType.START) {
            task.setState(new TaskOngoing());
        } else if (actionType == ActionType.EXPIRE) {
            task.setState(new TaskExpired());
        }
    }
}
