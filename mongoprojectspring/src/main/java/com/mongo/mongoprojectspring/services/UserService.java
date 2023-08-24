package com.mongo.mongoprojectspring.services;

import com.mongo.mongoprojectspring.dto.UserDTO;
import com.mongo.mongoprojectspring.entities.User;
import com.mongo.mongoprojectspring.exception.ObjectNotFoundException;
import com.mongo.mongoprojectspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
  }

  public User insert(User obj) {
    return userRepository.insert(obj);
  }

  public void delete(String id) {
    findById(id);
    userRepository.deleteById(id);
  }

  public User update(User obj) {
    User user = this.findById(obj.getId());
    user.setName(obj.getName());
    user.setEmail(obj.getEmail());
    userRepository.save(user);
    return user;
  }

  public User fromDTO(UserDTO objDTO) {
    return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
  }


}
