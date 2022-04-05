package com.example.dp.service;

import com.example.dp.entity.Task;
import com.example.dp.enums.ActionType;

/**
 * desc
 *
 * @author nexus 2022/03/13 21:31
 */
public interface State {
    // 默认实现，不做任何处理
    default void update(Task task, ActionType actionType) {
        // do nothing
    }
}
