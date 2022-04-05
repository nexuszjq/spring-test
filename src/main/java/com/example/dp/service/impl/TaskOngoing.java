package com.example.dp.service.impl;

import com.example.dp.entity.Task;
import com.example.dp.enums.ActionType;
import com.example.dp.service.State;
import com.example.dp.service.Subject;

/**
 * desc
 *
 * @author nexus 2022/03/13 21:35
 */
public class TaskOngoing extends Subject implements State {
    @Override
    public void update(Task task, ActionType actionType) {
        if (actionType == ActionType.ACHIEVE) {
            task.setState(new TaskFinished());
            // 通知
            notifyObserver(task.getTaskId());
        } else if (actionType == ActionType.STOP) {
            task.setState(new TaskPaused());
        } else if (actionType == ActionType.EXPIRE) {
            task.setState(new TaskExpired());
        }
    }
}
