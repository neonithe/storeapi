package se.web.store.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

    /** Error messages **/
    public static final String NAME_ERROR       = "Name has to few characters [1-100]";
    public static final String DESC_ERROR       = "Description has to few characters [1-100]";

    @NotBlank(message = "Product name is required")
    @Size(min = 1, max = 100, message = NAME_ERROR)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 100, message = DESC_ERROR)
    private String description;

    @NotBlank(message = "Need to add at least stock picture")
    private String image;

    @NotNull
    @Min(value = 1, message = "Can not be zero")
    private double price;

    @NotNull
    @Min(value = 1, message = "Can not be zero")
    private int quantity;


    /** Getters And Setters **/

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
    public String toString() {
        return "ProductDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
