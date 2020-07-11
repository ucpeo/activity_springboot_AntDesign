package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Option {
    private  Integer id;
    private  String value;

    @JSONField(serialize = false)
    private  Boolean right;

    @JSONField(serialize = false)
    ActForm actForm ;

}
