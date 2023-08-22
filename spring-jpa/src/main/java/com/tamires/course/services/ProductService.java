package com.tamires.course.services;

import com.tamires.course.entities.Product;
import com.tamires.course.entities.User;
import com.tamires.course.repositories.ProductRepository;
import com.tamires.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product.get();
  }
}
