package com.ucpeo.activity.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resp<T> {
    public String msg = "success";
    public Integer code = 0;
    public T data;

    public Resp(T data){
        this.data=data;
    }
    public Resp(Integer code ,String msg){
        this.code = code;
        this.msg=msg;
        this.data=null;
    }
}
