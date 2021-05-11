package com.pgbezerra.productservice.service.impl;

import com.pgbezerra.productservice.model.Product;
import com.pgbezerra.productservice.repository.ProductRepository;
import com.pgbezerra.productservice.service.ProductService;
import io.micrometer.core.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static java.util.Objects.requireNonNull;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(@NonNull Product product) {
        requireNonNull(product);
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Product with id %d not found", id)));
    }

    @Override
    public Product updateAll(Product product) {
        findById(product.getId());
        return save(product);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }

}
