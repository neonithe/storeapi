package se.web.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import se.web.store.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    //CRUD


}
