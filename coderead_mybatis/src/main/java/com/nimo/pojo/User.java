package com.nimo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: Dash
 * @ClassName: User
 * @description:
 * @author: chuf
 * @create: 2022-04-12 23:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private int age;

    private String sex;
}
