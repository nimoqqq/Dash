package com.example.demo.objectIsNull;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Order implements Serializable {

    private int orderId;
}
