package com.example.tpathletics.DTO;

/*
Data Transfer Object (DTO) to simplify communication between the spring app + database and Angular.
This applies to the cart items
 */

public class CartItemRequest {
    private Long productId;
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
