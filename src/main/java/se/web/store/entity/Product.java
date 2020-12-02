package se.web.store.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image", length = 45, nullable = true)
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    /** Constructors **/

    public Product() {
    }

    public Product(String title, String description, String image, double price, int quantity) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(Integer productId, String title, String description, String image, double price, int quantity) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    /** Getters and setters **/

    public Integer getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** Other **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(title, product.title) &&
                Objects.equals(description, product.description) &&
                Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, description, image, price, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    /** IMAGE **/

    @Transient
    public String getImagePath() {
        if(image == null || productId == null) return null;
        return "/resources/images/" + productId + "/" + image;
    }



}
