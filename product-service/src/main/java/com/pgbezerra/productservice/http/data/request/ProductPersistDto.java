package com.pgbezerra.productservice.http.data.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(name = "Product create")
public class ProductPersistDto {

    @NotBlank(message = "Product description cannot be empty or null")
    private String description;

    @NotNull(message = "Product value cannot be null")
    @DecimalMin(value = "0.01", message = "Product value cannot be less than $ 0.01")
    private BigDecimal value;

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }
}
