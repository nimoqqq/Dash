package com.chuf.springcachedemo.services;

import com.chuf.springcachedemo.domain.UserDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable(value="user", key="#id")
    public UserDTO getUserById(Long id) {
        UserDTO userDTO = new UserDTO();
        return userDTO;
    }

}
