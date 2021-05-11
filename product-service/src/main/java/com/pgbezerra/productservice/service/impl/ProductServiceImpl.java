package com.pgbezerra.productservice.service.impl;

import com.pgbezerra.productservice.model.Product;
import com.pgbezerra.productservice.repository.ProductRepository;
import com.pgbezerra.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Product with id %d not found", id)));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
