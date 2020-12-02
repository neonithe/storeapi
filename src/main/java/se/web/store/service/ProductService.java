package se.web.store.service;

import se.web.store.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Integer id);
}
