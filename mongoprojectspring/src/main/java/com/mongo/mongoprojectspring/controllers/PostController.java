package com.mongo.mongoprojectspring.controllers;

import com.mongo.mongoprojectspring.entities.Post;
import com.mongo.mongoprojectspring.services.PostService;
import com.mongo.mongoprojectspring.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
  @Autowired
  PostService postService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post post = postService.findById(id);
    return ResponseEntity.ok().body(post);
  }

  @GetMapping(value = "/titlesearch")
  public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
    String textDecode = URL.decodeParam(text);
    List<Post> posts = postService.findByTitle(textDecode);
    return ResponseEntity.ok().body(posts);
  }

  @GetMapping(value = "/titlesearch2")
  public ResponseEntity<List<Post>> findByTitle2(@RequestParam(value = "text", defaultValue = "") String text) {
    String textDecode = URL.decodeParam(text);
    List<Post> posts = postService.findByTitle2(textDecode);
    return ResponseEntity.ok().body(posts);
  }

  @GetMapping(value = "/fullsearch")
  public ResponseEntity<List<Post>> fullSearch(
    @RequestParam(value = "text", defaultValue = "") String text,
    @RequestParam(value = "minDate", defaultValue = "") String minDate,
    @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
    String textDecode = URL.decodeParam(text);
    LocalDate minDateConv = URL.convertDate(minDate, LocalDate.now().minusYears(100));
    LocalDate maxDateConv = URL.convertDate(maxDate, LocalDate.now());
    List<Post> posts = postService.fullSearch(textDecode, minDateConv, maxDateConv);
    return ResponseEntity.ok().body(posts);
  }
}
