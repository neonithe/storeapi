package se.web.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.web.store.dao.AdminUserDAO;
import se.web.store.dao.ProductDAO;
import se.web.store.entity.AdminUser;
import se.web.store.entity.Product;
import se.web.store.security.PasswordGenerator;

@SpringBootApplication
public class StoreApplication implements CommandLineRunner {

	@Autowired
	ProductDAO productDAO;

	@Autowired
	AdminUserDAO adminUserDAO;

	private PasswordGenerator gen = new PasswordGenerator();

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		productDAO.save(new Product("Title 1", "Description 1", "https://via.placeholder.com/150x100", 100, 100));
		productDAO.save(new Product("Title 2", "Description 2", "https://via.placeholder.com/150x100", 200, 200));
		productDAO.save(new Product("Title 3", "Description 3", "https://via.placeholder.com/150x100", 300, 300));
		productDAO.save(new Product("Title 4", "Description 4", "https://via.placeholder.com/150x100", 400, 400));
		productDAO.save(new Product("Title 5", "Description 5", "https://via.placeholder.com/150x100", 500, 500));

		String pass = gen.passwordGenerator("admin");

		adminUserDAO.save(new AdminUser("admin@admin.com",pass,"ROLE_ADMIN",true));

	}
}
