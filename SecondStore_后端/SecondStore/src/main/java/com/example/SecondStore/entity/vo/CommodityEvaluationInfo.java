package com.example.SecondStore.entity.vo;

import com.example.SecondStore.entity.CommodityEvaluation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityEvaluationInfo extends CommodityEvaluation {
    private String name;    //userName
}
