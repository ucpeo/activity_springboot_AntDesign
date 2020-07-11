package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;


/*
 * 用户参与表单填写内容
 * */
@Data
public class PartForm{

    private Integer id;
    @JSONField(serialize = true)
    private Partake partake;//参与事件


    private ActForm actForm; //表单

    private String value; //值
    private Integer   type;
    private List<Option> optionList; //选项

}

