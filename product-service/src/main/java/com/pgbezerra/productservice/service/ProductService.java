package com.pgbezerra.productservice.service;

import com.pgbezerra.productservice.model.Product;
import io.micrometer.core.lang.NonNull;

public interface ProductService {

    Product save(@NonNull Product product);
    Product findById(Long id);
    Product updateAll(Product product);
    void deleteById(Long id);

}
