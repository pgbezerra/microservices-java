package com.pgbezerra.productservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgbezerra.productservice.event.ProductPersistEvent;
import com.pgbezerra.productservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductPersisQueueListener implements ApplicationListener<ProductPersistEvent> {

    private static final Logger log = LoggerFactory.getLogger(ProductPersisQueueListener.class);

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public ProductPersisQueueListener(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(ProductPersistEvent productPersistEvent) {
        try {
            Product product = productPersistEvent.getProduct();
            String json = objectMapper.writeValueAsString(product);
            jmsTemplate.convertAndSend("product.persist.queue", json);
        } catch (JsonProcessingException e) {
            log.error(String.format("Error on send product to product queue: %s", e.getMessage()), e);
        }
    }

}
