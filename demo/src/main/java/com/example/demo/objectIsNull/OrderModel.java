package com.example.demo.objectIsNull;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderModel implements Serializable {
    private static final long serialVersionUID = -3430648686732994255L;
    private Integer totalCount;
    private List<Order> orderList;
}
