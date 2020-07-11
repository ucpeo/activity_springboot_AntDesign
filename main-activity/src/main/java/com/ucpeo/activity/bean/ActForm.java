package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ActForm {
    private Integer id;
    private String name;
    private Integer type;

    @JSONField(serialize = false)
    private Act act;

    List<Option> optionList;

}
