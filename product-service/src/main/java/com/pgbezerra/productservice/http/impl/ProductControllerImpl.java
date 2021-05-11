package com.pgbezerra.productservice.http.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
        return productService.save(new Product(productPersistDto.getDescription(), productPersistDto.getValue()));
    }

    @Override
    @PatchMapping(value = "{id}")
    public Product update(@PathVariable(value = "id") Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Product product = findById(id);
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
        JsonNode productJsonNode = objectMapper.convertValue(product, JsonNode.class);
        JsonNode patchJsonNode = patch.apply(productJsonNode);
        Product productPersist = objectMapper.treeToValue(patchJsonNode, Product.class);
        return productService.save(productPersist);
    }

    @Override
    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(value = "id") Long id){
        productService.deleteById(id);
    }


    @Override
    @GetMapping(value = "{id}")
    public Product findById(@PathVariable(value = "id") Long id){
        return productService.findById(id);
    }

    @Override
    @PutMapping(value = "{id}")
    public Product updateAll(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductPersistDto productPersistDto) {
        Product product = new Product(id, productPersistDto.getDescription(), productPersistDto.getValue());
        return productService.updateAll(product);
    }
}
