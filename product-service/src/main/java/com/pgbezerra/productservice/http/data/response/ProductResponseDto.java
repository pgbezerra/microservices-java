package com.pgbezerra.productservice.http.data.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Product response")
public class ProductResponseDto {

    private Long id;
    private String description;

    @Deprecated
    public ProductResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
