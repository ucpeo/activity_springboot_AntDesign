package com.ucpeo.activity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User  implements Serializable {
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
    transient  List<Act> actList;

    @JSONField(serialize = false)
    transient List<Partake> partakeList;
}
