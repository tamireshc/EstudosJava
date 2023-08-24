package com.mongo.mongoprojectspring.repositories;

import com.mongo.mongoprojectspring.entities.Post;
import com.mongo.mongoprojectspring.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
