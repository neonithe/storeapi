package se.web.store.serviceimpl;

import org.springframework.stereotype.Service;
import se.web.store.dao.ProductDAO;
import se.web.store.entity.Product;
import se.web.store.service.MyConversionService;
import se.web.store.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private MyConversionService service;

    public ProductServiceImpl(ProductDAO productDAO, MyConversionService service) {
        this.productDAO = productDAO;
        this.service = service;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return service.getProductObject(id);
    }
}
