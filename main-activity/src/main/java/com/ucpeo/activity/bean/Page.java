package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;


@Data
public class Page<T> extends Resp<List<T>> {
    private Integer curr ; // 当前页
    private Integer count; //总页数
    private Integer size; //每页数量
    private Integer pageCount;

    @JSONField(serialize = false)
    private Integer start = 0; //起始

    @JSONField(serialize = false)
    private Integer end = 10; //结束

    public Page(){
        curr=1;
        size=10;
    }

    @Override
    public String toString() {
        return "Page{" +
                "curr=" + curr +
                ", count=" + count +
                ", size=" + size +
                ", pageCount=" + pageCount +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public void render() {
        if (count == null || curr < 1)
            return;
        if (size < 1)
            size = 10;
        pageCount=count%size==0?count/size:count/size+1;
        start = (curr - 1) * size;
        end = size;

    }

}
