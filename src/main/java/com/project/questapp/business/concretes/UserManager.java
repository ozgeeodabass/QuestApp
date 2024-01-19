package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.UserRepository;
import com.project.questapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {

    private UserRepository repository;
    @Autowired
    public UserManager(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(User user) {
        repository.save(user);
    }

    @Override
    public void delete(long id) {
        User userFound = repository.findById(id).get();
        repository.delete(userFound);
    }

    @Override
    public User update(long id, User user) {
        Optional<User> userFoundOptional = repository.findById(id);
        User userFound = userFoundOptional.get();
        if(userFound!=null&&userFound.getId()==id){
            userFound.setUserName(user.getUserName());
            userFound.setPassword(user.getPassword());
            repository.save(userFound);
            return userFound;
        }else
            return null;
    }

    @Override
    public User getByUserId(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findByUserName(String name) {
        User userFound = repository.findByUserName(name);
        if(userFound!=null)
            return userFound;
        return null;
    }
}
