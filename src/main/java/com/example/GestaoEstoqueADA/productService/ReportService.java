package com.example.GestaoEstoqueADA.productService;

import com.example.GestaoEstoqueADA.model.Product;
import com.example.GestaoEstoqueADA.productRepository.RepositoryInt;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final RepositoryInt<Product> repositoryInt;

    public ReportService(RepositoryInt<Product> repositoryInt) {
        this.repositoryInt = repositoryInt;
    }

    @PostConstruct
    public List<Product> allProducts() {
        return repositoryInt.show();
    }

    public void averageValue(List<Product> products) {
        BigDecimal total = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = total.divide(BigDecimal.valueOf(products.size()), RoundingMode.HALF_UP);
        System.out.printf("%nMédia de preço de todos os produtos: R$%.2f%n", average);
    }
    public void categoriesProducts(List<Product> products) {
        Map<String, List<Product>> categoryProducts = new HashMap<>();
        Set<String> categoriesSet = categories(products);
        categoriesSet.forEach(category -> categoryProducts.put(category, new ArrayList<>()));
        products.forEach(product -> categoryProducts.get(product.getCategory()).add(product));
        System.out.printf("%n%s%n", "Produtos por Categoria");
        categoryProducts.forEach((key, value) -> System.out.printf("%s -> %d produtos%n", key, value.size()));
    }
    public void few(List<Product> products) {
        List<Product> fewUnits = products.stream().filter(product -> product.getAmount() < 10).toList();
        System.out.printf("% Produtos com menos de 10 unidades: %s%n", fewUnits);
    }

    public void productFilter(List<Product> products, Predicate<Product> predicate) {
        List<Product> fewUnits = products.stream().filter(predicate).toList();
        System.out.printf("%nProdutos selecionados: %s%n", fewUnits);
    }
    public Set<String> categories(List<Product> products) {
        return products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
    }
}
