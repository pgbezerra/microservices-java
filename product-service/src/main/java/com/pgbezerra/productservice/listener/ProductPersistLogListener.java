package com.pgbezerra.productservice.listener;

import com.pgbezerra.productservice.event.ProductPersistEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistLogListener  implements ApplicationListener<ProductPersistEvent> {

    private static final Logger log = LoggerFactory.getLogger(ProductPersisQueueListener.class);

    @Override
    public void onApplicationEvent(ProductPersistEvent productPersistEvent) {
        log.info(productPersistEvent.getProduct().toString());
    }
}
