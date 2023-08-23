package com.tamires.course.controllers;

import com.tamires.course.entities.User;
import com.tamires.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> users = userService.findAll();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping
  public ResponseEntity<User> insert(@RequestBody User obj) {
    User user = userService.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
      .path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).body(user);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User>update(@PathVariable Long id, @RequestBody User obj){
    User user = userService.update(id, obj);
    return ResponseEntity.ok().body(user);
  }
}
