package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {

    private Integer id;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private Integer state = 0;
    private Date birthday;
    @JSONField(serialize = false)
    List<Act> actList;

    @JSONField(serialize = false)
    List<Partake> partakeList;
}
