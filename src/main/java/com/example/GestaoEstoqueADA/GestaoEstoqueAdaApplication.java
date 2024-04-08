package com.example.GestaoEstoqueADA;

import com.example.GestaoEstoqueADA.model.Product;
import com.example.GestaoEstoqueADA.productService.DbService;
import com.example.GestaoEstoqueADA.productService.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class GestaoEstoqueAdaApplication implements CommandLineRunner {
    private final ReportService reportService;
    private final DbService dbService;

    public GestaoEstoqueAdaApplication(ReportService reportService, DbService dbService) {
        this.reportService = reportService;
        this.dbService = dbService;
    }


    public static void main(String[] args) {

        SpringApplication.run(GestaoEstoqueAdaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        dbService.initializer();
        List<Product> products = reportService.allProducts();
        products.forEach(System.out::println);
        Set<String> categories = reportService.categories(products);
        System.out.printf("Categorias únicas: %d%n", categories.size());
        System.out.printf("Categorias: %s%n", categories);

        reportService.averageValue(products);

        reportService.categoriesProducts(products);
        reportService.productFilter(products, product -> product.getAmount() <= 3);

        reportService.few(products);

    }
}
