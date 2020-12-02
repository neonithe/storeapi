package se.web.store.service;

import org.springframework.stereotype.Service;
import se.web.store.dao.AdminUserDAO;
import se.web.store.dao.ProductDAO;
import se.web.store.entity.AdminUser;
import se.web.store.entity.Product;

import java.util.Optional;

@Service
public class MyConversionService {

    private ProductDAO productDAO;
    private AdminUserDAO adminUserDAO;

    public MyConversionService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /** Converter Optional to product Object **/

    public Product getProductObject(Integer id) {
        Product product = null;

        Optional<Product> productList = productDAO.findById(id);
        if ( productList.isPresent() ) {
            product = productList.get();
        }
        return product;
    }

    /** Converter Optional to AdminUser Object **/

    public AdminUser getUserObject(Integer id) {
        AdminUser user = null;

        Optional<AdminUser> userList = adminUserDAO.findById(id);
        if ( userList.isPresent() ) {
            user = userList.get();
        }
        return user;
    }
}
