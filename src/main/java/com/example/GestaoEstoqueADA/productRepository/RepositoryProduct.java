package com.example.GestaoEstoqueADA.productRepository;

import com.example.GestaoEstoqueADA.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RepositoryProduct implements RepositoryInt<Product> {
    private final JdbcTemplate jdbcTemplate;

    public RepositoryProduct(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> Show() {
        String sql = "SELECT * FROM produtos";
        RowMapper<Product> rowMapper = ((rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("amount"),
                rs.getString("category"),
                rs.getBigDecimal("price")
        ));
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Product> show() {
        return null;
    }

    public void save(Product product) {
        String sql = "INSERT INTO products (id, name, amount, category, price, low) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                product.getId(),
                product.getName(),
                product.getAmount(),
                product.getCategory(),
                product.getPrice());
    }
}
