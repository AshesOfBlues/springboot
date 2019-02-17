package com.alex.blog.repository;

import com.alex.blog.domain.User;

import java.util.List;

public interface UserRepository {
    /**
     * 创建或修改用户
     */
    User saveOrUpdateUser(User user);
    /*
    * 删除用户
    * */
    void deleteUser(Long id);
    /**
     * 根据id查询用户
     */
    User getUserById(Long id);
    /**
     * 获取用户列表
     */
    List<User> listUsers();
}
