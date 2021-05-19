package com.pgbezerra.productservice.event;

import com.pgbezerra.productservice.model.Product;
import org.springframework.context.ApplicationEvent;

public class ProductPersistEvent extends ApplicationEvent {

    private final Product product;

    public ProductPersistEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
