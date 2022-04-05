package com.example.springtest.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/27 20:32
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -508233980253002780L;
    private Long userId;
    private String userName;
}
