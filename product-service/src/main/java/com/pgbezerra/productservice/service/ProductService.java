package com.pgbezerra.productservice.service;

import com.pgbezerra.productservice.model.Product;
import io.micrometer.core.lang.NonNull;

public interface ProductService {

    Product insert(@NonNull Product product);
    Product findById(Long id);

}
