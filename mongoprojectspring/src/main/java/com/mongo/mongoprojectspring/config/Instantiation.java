package com.mongo.mongoprojectspring.config;

import com.mongo.mongoprojectspring.entities.User;
import com.mongo.mongoprojectspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
// CommandLineRunner executa quando o projeto é iniciado
public class Instantiation implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;
  @Override
  public void run(String... args) throws Exception {

    userRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));


  }
}
