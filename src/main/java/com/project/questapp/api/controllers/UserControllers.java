package com.project.questapp.api.controllers;

import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
//@CrossOrigin("*")
public class UserControllers {
    private UserService service;
    @Autowired
    public UserControllers(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public void add(@RequestBody User user){
         this.service.add(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        this.service.delete(id);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable long id,@RequestBody  User user) {
        return this.service.update(id,user);
    }

    @GetMapping("/getbyid/{id}")
    public User getByUserId(@PathVariable long id) {
        return this.service.getByUserId(id);
    }

    @GetMapping("/getall")
    public List<User> getAll(){
        return this.service.getAll();
    }

    @GetMapping("/getbyusername/{name}")
    public User findByUserName(@PathVariable String name){ return this.service.findByUserName(name);}
}
