package com.ssm.mapper;

import com.ssm.pojo.User;

public interface UserMapper {
    User login(User user);
    User getUserByName(Integer id);
}
