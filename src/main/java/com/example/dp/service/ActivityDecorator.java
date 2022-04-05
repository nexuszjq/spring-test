package com.example.dp.service;

/**
 * desc
 *
 * @author nexus 2022/03/13 22:17
 */
public abstract class ActivityDecorator implements ActivityInterface {
    protected ActivityInterface activity;
    public ActivityDecorator(ActivityInterface activity) {
        this.activity = activity;
    }
    public abstract void participate(Long userId);
}