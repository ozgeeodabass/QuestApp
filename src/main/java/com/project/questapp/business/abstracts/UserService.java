package com.project.questapp.business.abstracts;

import com.project.questapp.dataAccess.UserRepository;
import com.project.questapp.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void add(User user);

    void delete(long id);

    User update(long id,User user);

    User getByUserId(long id);

    User findByUserName(String name);
}
