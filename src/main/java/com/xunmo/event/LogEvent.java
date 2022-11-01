package com.xunmo.event;

import lombok.Getter;

@Getter
public class LogEvent {
    private String msg;
    public LogEvent(String msg){
        this.msg = msg;
    }
}
