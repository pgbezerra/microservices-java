package com.pgbezerra.productservice.http.impl;

import com.pgbezerra.productservice.http.ProductController;
import com.pgbezerra.productservice.http.data.request.ProductPersistDto;
import com.pgbezerra.productservice.model.Product;
import com.pgbezerra.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Product")
@RestController
@RequestMapping(value = "/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    public ProductControllerImpl(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product insert(@RequestBody @Valid ProductPersistDto productPersistDto){
        return productService.insert(new Product(productPersistDto.getDescription(), productPersistDto.getValue()));
    }


    @Override
    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable(value = "id") Long id){
        return productService.findById(id);
    }

}
