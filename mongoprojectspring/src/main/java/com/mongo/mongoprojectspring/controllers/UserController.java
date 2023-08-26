package com.mongo.mongoprojectspring.controllers;

import com.mongo.mongoprojectspring.dto.UserDTO;
import com.mongo.mongoprojectspring.entities.Post;
import com.mongo.mongoprojectspring.entities.User;
import com.mongo.mongoprojectspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> users = userService.findAll();
    List<UserDTO> usersDTO = users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    return ResponseEntity.ok().body(usersDTO);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User user = userService.findById(id);
    UserDTO userDTO = new UserDTO(user);
    return ResponseEntity.ok().body(userDTO);
  }

  @PostMapping
  public ResponseEntity<User> insert(@RequestBody UserDTO objDTO) {
    User user = userService.insert(userService.fromDTO(objDTO));
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
    User user = userService.fromDTO(userDTO);
    user.setId(id);
    User userAtt = userService.update(user);
    return ResponseEntity.ok().body(userAtt);
  }

  @GetMapping(value = "/{id}/posts")
  public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
    User user = userService.findById(id);
    return ResponseEntity.ok().body(user.getPosts());
  }
}
