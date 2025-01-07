package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> usersList = service.findAll();
        List<UserDTO> userDTOList = usersList.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = service.findById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO userDTO){
        User user = service.fromDTO(userDTO);
        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.getId())
                        .toUri();
        service.insert(user);
        return ResponseEntity.created(uri).body(user);
    }
}
