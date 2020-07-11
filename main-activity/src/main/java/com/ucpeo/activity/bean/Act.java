package com.ucpeo.activity.bean;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Act {
    private Integer id;
    private String name;
    private Date createTime;
    private String describe;
    private Integer checkLevel;
    private Integer state;
    private Integer participants;
    private Integer recruited;
    private Integer type;
    private Date endTime;
    private Boolean enRes ;
    private Boolean enForm ;
    @JSONField(serialize = false)
    private User createUser;

    @JSONField(serialize = false)
    List<Res> resList;

    @JSONField(serialize = false)
    List<ActForm> actFormList;


}
