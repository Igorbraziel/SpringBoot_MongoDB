package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;

import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
    }
}
