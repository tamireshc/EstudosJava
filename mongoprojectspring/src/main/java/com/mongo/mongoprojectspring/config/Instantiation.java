package com.mongo.mongoprojectspring.config;

import com.mongo.mongoprojectspring.dto.AuthorDTO;
import com.mongo.mongoprojectspring.dto.CommentDTO;
import com.mongo.mongoprojectspring.entities.Post;
import com.mongo.mongoprojectspring.entities.User;
import com.mongo.mongoprojectspring.repositories.PostRepository;
import com.mongo.mongoprojectspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
// CommandLineRunner executa quando o projeto é iniciado
public class Instantiation implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {
    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    userRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    CommentDTO c1 = new CommentDTO("Boa viagem mano",LocalDate.parse("21/03/2018", fmt1),new AuthorDTO(alex));
    CommentDTO c2 = new CommentDTO("Aproveite",LocalDate.parse("21/03/2018", fmt1),new AuthorDTO(bob));
    CommentDTO c3 = new CommentDTO("Tenha um ótimo dia",LocalDate.parse("21/03/2018", fmt1),new AuthorDTO(alex));

    Post post1 = new Post(null, LocalDate.parse("21/03/2018", fmt1), "Partiu viagem", "Vou viajar para Sãp Paulo", new AuthorDTO(maria));
    Post post2 = new Post(null, LocalDate.parse("23/03/2018", fmt1), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));

    post1.getComments().add(c1);
    post1.getComments().add(c2);
    post2.getComments().add(c3);

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().add(post1);
    maria.getPosts().add(post2);

    userRepository.save(maria);
  }
}
