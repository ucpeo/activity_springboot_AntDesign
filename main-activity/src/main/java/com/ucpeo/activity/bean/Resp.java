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
}
