package com.example.dp.entity;

import com.example.dp.enums.ActionType;
import com.example.dp.service.State;
import com.example.dp.service.impl.TaskInit;
import lombok.Data;

/**
 * desc
 *
 * @author nexus 2022/03/13 21:38
 */
@Data
public class Task {
    private Long taskId;
    // 初始化为初始态
    private State state = new TaskInit();
    // 更新状态
    public void updateState(ActionType actionType) {
        state.update(this, actionType);
    }
}
