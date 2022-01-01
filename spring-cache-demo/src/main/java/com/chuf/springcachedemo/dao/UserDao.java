package com.chuf.springcachedemo.dao;


import com.chuf.springcachedemo.domain.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDao {
    private static List<UserDTO> lists = new ArrayList<>();

    static {
        lists.add(new UserDTO(1L, "小红"));
        lists.add(new UserDTO(2L, "小明"));
        lists.add(new UserDTO(3L, "小强"));
        lists.add(new UserDTO(4L, "小花"));
    }

    public void addUser(UserDTO user) {
        lists.add(user);
    }

    public UserDTO deleteUser(UserDTO user) {
        for (UserDTO userDTO : lists) {
            if (Objects.equals(userDTO.getId(), user.getId()) && Objects.equals(userDTO.getName(), user.getName())) {
                lists.remove(userDTO);
                return user;
            }
        }
        return null;
    }

    public UserDTO getUserById(Long id) {
        for (UserDTO userDTO : lists) {
            if (Objects.equals(userDTO.getId(), id)) {
                return userDTO;
            }
        }
        return null;
    }
}
