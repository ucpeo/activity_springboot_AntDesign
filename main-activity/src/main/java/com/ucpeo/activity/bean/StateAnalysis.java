package com.ucpeo.activity.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract class StateAnalysis {
    private  Integer state;
    private Integer count;
}
