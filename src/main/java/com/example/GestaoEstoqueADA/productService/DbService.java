package com.example.GestaoEstoqueADA.productService;

import com.example.GestaoEstoqueADA.model.Product;
import com.example.GestaoEstoqueADA.productRepository.RepositoryInt;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Component
public class DbService {
    private final RepositoryInt<Product> repositoryInt;

    public DbService(RepositoryInt<Product> repositoryInt) {
        this.repositoryInt = repositoryInt;
    }

    private List<Product> loadCsv() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/com/example/DataBase/productList.csv").toAbsolutePath());
        CsvToBean<Product> mapper = new CsvToBeanBuilder<Product>(reader)
                .withType(Product.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return mapper.parse();
    }

    private void Dbsave(List<Product> produtos) {
        produtos.forEach(repositoryInt::save);
    }

    public void initializer() throws IOException {
        try {
            List<Product> produtos = loadCsv();
            Dbsave(produtos);
            System.out.println("Base de dados iniciada!");
        } catch (Exception e) {
            System.out.println("Banco já em execução");
        }
    }
}