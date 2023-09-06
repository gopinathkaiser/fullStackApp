package com.example.UserManagement.Controller;

import com.example.UserManagement.Repository.UserRepository;
import com.example.UserManagement.Model.Users;
import com.example.UserManagement.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Users insertData(@RequestBody Users user){
        Timestamp ts = new Timestamp(new Date().getTime());
        user.setModifyDate(ts);
        user.setCreateDate(ts);
        return userService.addUser(user);

    }
//    @GetMapping
//    public List<Users> display(){
//        return (List<Users>) userService.findByModified();
//    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable String email){
         return userService.deleteUser(email);

    }

    @GetMapping("/{email}")
    public Optional<Users> displayDataFetch(@PathVariable String email){

        return userService.displayDataFetch(email);
    }

    @PutMapping
    public Users insertAfterEditData(@RequestBody Users user){
        return userService.insertAfterDataEdited(user);
    }


    @GetMapping("/verify/{email}")
    public boolean checkEmail(@PathVariable String email){
        return userService.checkEmail(email);
    }

    @GetMapping("/{page}/{size}")
    public List<Users> displayPaging(@PathVariable int page, @PathVariable int size){
        Pageable pageable = PageRequest.of(page, size);
        return userService.findByModified(pageable);
    }

    @GetMapping("/countData")
    public long countData(){

        return userService.findCount();
    }
}


