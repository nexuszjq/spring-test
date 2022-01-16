package com.example.springtest.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 18:47
 */
@Data
public class ResourceLock implements Serializable {

    private static final long serialVersionUID = 3841570322637044662L;

    private Long id;
    private String resourceName;
    private String nodeInfo;
    private Long count;
    private Date createTime;
    private Date updateTime;
}
