package com.pgbezerra.productservice.listener;

import com.pgbezerra.productservice.event.ProductPersistEvent;
import com.pgbezerra.productservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistLogListener  implements ApplicationListener<ProductPersistEvent> {

    private static final Logger log = LoggerFactory.getLogger(ProductPersistLogListener.class);

    @Override
    public void onApplicationEvent(ProductPersistEvent productPersistEvent) {
        final Product product = productPersistEvent.getProduct();
        log.info(String.format("New product inserted: %s", product.toString()));
    }
}
