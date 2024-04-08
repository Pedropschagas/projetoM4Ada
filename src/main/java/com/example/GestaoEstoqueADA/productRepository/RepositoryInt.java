package com.example.GestaoEstoqueADA.productRepository;

import java.util.List;

public interface RepositoryInt<T> {
    List<T> show();
    void save(T t);
}
