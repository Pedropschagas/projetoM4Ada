package com.example.GestaoEstoqueADA.productRepository;

import com.example.GestaoEstoqueADA.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
