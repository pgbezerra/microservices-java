package com.pgbezerra.productservice.model;

import io.micrometer.core.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.hash;

@Entity
@Table(name = "TB_PRODUCT")
public class Product {

    @Id
    @Column(name = "ID_PRODUCT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "DS_PRODUCT", nullable = false, length = 100)
    private String description;
    @Column(name = "VL_PRODUCT", nullable = false)
    private BigDecimal value;

    @Deprecated
    public Product() {
    }

    public Product(@NonNull String description, @NonNull BigDecimal value) {
        this.description = requireNonNull(description);
        this.value = requireNonNull(value);
    }

    public Product(@NonNull Long id, @NonNull String description, @NonNull BigDecimal value) {
        this.id = requireNonNull(id);
        this.description = requireNonNull(description);
        this.value = requireNonNull(value);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
