package se.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.web.store.dao.ProductDAO;
import se.web.store.dto.ProductDTO;
import se.web.store.entity.Product;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/product", method = RequestMethod.GET)
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    /** DTO -------------------- **/

    /** Save product **/
    @GetMapping("admin/add/product")
    public String addProduct(Model model) {
        ProductDTO dto = new ProductDTO();
        //Product product = new Product();
        model.addAttribute("saveProduct", dto);
        model.addAttribute("image", "/resources/images/default/empty.png");

        return "admin/admin-product-form.html";
    }

    @PostMapping("admin/add/product/save")
    public String saveProduct(
            @Valid @ModelAttribute("saveProduct") ProductDTO dto, BindingResult errors,
            @RequestParam("fileImage") MultipartFile file ) throws IOException {

        // If any errors found
        if ( errors.hasErrors() ) {
            return "admin/admin-product-form.html";
        }

        if ( dto.getImage().isEmpty() ) {
            dto.setImage("/resources/images/default/empty.png");
        }

        // Create new product - add the information from form
        Product product =
                new Product(
                        dto.getTitle(),
                        dto.getDescription(),
                        dto.getImage(),
                        dto.getPrice(),
                        dto.getQuantity());

        if ( file.isEmpty() ) {

        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        product.setImage(fileName);
        Product saveProduct = productDAO.save(product);

        String uploadDir = "./resources/images/" + saveProduct.getProductId();
        Path uploadPath = Paths.get(uploadDir);


        if ( !Files.exists(uploadPath) ) {
            Files.createDirectories(uploadPath);
        }

        try ( InputStream inputStream = file.getInputStream() ) {
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }

        saveProduct.setImage(saveProduct.getImagePath());
        productDAO.save(saveProduct);

        return "redirect:/admin";
    }


    /** DTO END -------------------- **/

    /** ADMIN - List products **/
    @GetMapping("admin/products")
    public String getProductList(Model model) {
        List<Product> productList = productDAO.findAll();
        model.addAttribute("productList", productList);

        return "admin/admin-product-list.html";
    }



    /** ADMIN - Add product
    @GetMapping("admin/add/product")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("saveProduct", product);
        model.addAttribute("image", "/resources/images/default/empty.png");

        return "admin/admin-product-form.html";
    }

    @PostMapping("admin/add/product/save")
    public String saveProduct(
            @ModelAttribute("saveProduct") Product product,
            @RequestParam("fileImage") MultipartFile file ) throws IOException {

        if ( file.isEmpty() ) {

        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        product.setImage(fileName);
        Product saveProduct = productDAO.save(product);

        String uploadDir = "./resources/images/" + saveProduct.getProductId();
        Path uploadPath = Paths.get(uploadDir);


        if ( !Files.exists(uploadPath) ) {
            Files.createDirectories(uploadPath);
        }

        try ( InputStream inputStream = file.getInputStream() ) {
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }

        saveProduct.setImage(saveProduct.getImagePath());
        productDAO.save(saveProduct);

        return "redirect:/admin";
    }**/

    /** ADMIN - Update product **/
    @PostMapping("admin/product/update")
    public String updateProduct(
            @RequestParam("productId") Integer id, Model model) {

        Product product = getProductObject(id);
        model.addAttribute("image", product.getImage());

        model.addAttribute("updateProduct", product);

        return "admin/admin-product-update.html";

    }

    @PostMapping("admin/product/update/save")
    public String updateProductSave(
            @ModelAttribute("updateProduct") Product product,
            @RequestParam("productId") Integer id) {

        Product updateProduct = getProductObject(id);
        updateProduct.setTitle(product.getTitle());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct.setImage(product.getImage());

        return "redirect:/admin";

    }

    /** ADMIN - Delete product **/
    @GetMapping("admin/product/delete")
    public String deleteProduct(@RequestParam("productId") Integer id) {
        productDAO.deleteById(id);

        return "redirect:/admin";
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

}
