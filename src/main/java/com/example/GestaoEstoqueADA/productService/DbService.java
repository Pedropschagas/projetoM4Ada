package com.example.GestaoEstoqueADA.productService;

import com.example.GestaoEstoqueADA.model.Product;
import com.example.GestaoEstoqueADA.productRepository.ProductRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DbService {
    private final ProductRepository productRepository;

    public DbService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void initializer() {
        try {
            List<Product> products = loadCsv();
            productRepository.saveAll(products);
            System.out.println("Base de dados iniciada!");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar a base de dados: " + e.getMessage());
        }
    }

    private List<Product> loadCsv() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src\\main\\java\\com\\example\\GestaoEstoqueADA\\DataBase\\productList.csv").toAbsolutePath());
        CsvToBean<Product> mapper = new CsvToBeanBuilder<Product>(reader)
                .withType(Product.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return mapper.parse();
    }
}