package com.tamires.course.services;

import com.tamires.course.entities.User;
import com.tamires.course.exceptions.DatabaseException;
import com.tamires.course.exceptions.ResourceNotFoundException;
import com.tamires.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

  public User findById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User insert(User obj) {
    return userRepository.save(obj);
  }

  public void delete(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(id);
    }catch (DataIntegrityViolationException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  public User update(Long id, User obj) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    user.setName(obj.getEmail());
    user.setEmail(obj.getEmail());
    user.setPhone(obj.getPhone());
    userRepository.save(user);
    return user;
  }
}
