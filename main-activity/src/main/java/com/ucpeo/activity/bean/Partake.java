package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Partake {
    private Integer id;
    private Integer state;

    private User user;

    private Date submitTime;

    private Act act;

    @JSONField(serialize = false)
    List<PartForm> partFormInputList;


}
