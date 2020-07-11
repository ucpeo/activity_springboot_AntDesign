package com.ucpeo.activity.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Res {
    private Integer id;
    private Integer type;
    private String value;

    public Res(Integer type, String value) {
        super();
        this.type = type;
        this.value = value;
    }
}
