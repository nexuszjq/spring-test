package com.example.dp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * desc
 *
 * @author nexus 2022/03/13 21:32
 */
@AllArgsConstructor
@Getter
public enum ActionType {
    START(1, "开始"),
    STOP(2, "暂停"),
    ACHIEVE(3, "完成"),
    EXPIRE(4, "过期");
    private final int code;
    private final String message;
}
