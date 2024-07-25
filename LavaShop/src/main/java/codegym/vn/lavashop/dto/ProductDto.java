package codegym.vn.lavashop.dto;

import codegym.vn.lavashop.model.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductDto {
    @NotEmpty(message = "Khong duoc de trong")
    private String name;
    @Min(value = 0)
    @NotNull(message = "Khong duoc de trong")
    private Integer quantity;
    @Min(value = 0)
    @NotNull(message = "Khong duoc de trong")
    private Double price;
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
