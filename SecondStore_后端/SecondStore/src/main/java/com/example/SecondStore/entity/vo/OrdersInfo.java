package com.example.SecondStore.entity.vo;

import com.example.SecondStore.entity.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrdersInfo extends Orders {
    private Integer level;
}
