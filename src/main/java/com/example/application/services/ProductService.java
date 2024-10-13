package com.example.application.services;

import com.example.application.data.Product;
import com.example.application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public ProductService() {}

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Page<Product> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Product> list(Pageable pageable, Specification<Product> filter) {
        return repository.findAll(filter, pageable);
    }

    public Optional<List<Product>> getAll() {
        return Optional.of(repository.findAll());
    }

}
