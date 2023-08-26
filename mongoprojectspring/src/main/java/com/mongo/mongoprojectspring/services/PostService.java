package com.mongo.mongoprojectspring.services;

import com.mongo.mongoprojectspring.entities.Post;
import com.mongo.mongoprojectspring.exception.ObjectNotFoundException;
import com.mongo.mongoprojectspring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
  @Autowired
  PostRepository postRepository;

  public Post findById(String id) {
    Optional<Post> post = postRepository.findById(id);
    return post.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
  }

  public List<Post> findByTitle(String text) {
    return postRepository.findByTitleContainingIgnoreCase(text);
  }

  public List<Post> findByTitle2(String text) {
    return postRepository.searchTitle(text);
  }

  public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate) {
    return postRepository.fullSearch(text, minDate, maxDate.plusDays(1));
  }
}
