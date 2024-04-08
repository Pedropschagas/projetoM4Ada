package com.example.GestaoEstoqueADA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Product {

    private Long id;
    private String name;
    private Integer amount;
    private String category;
    private BigDecimal price;
}
