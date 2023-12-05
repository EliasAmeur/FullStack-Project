package com.example.tpathletics.rest;

import com.example.tpathletics.DTO.CartItemRequest;
import com.example.tpathletics.entity.Cart;
import com.example.tpathletics.entity.CartItem;
import com.example.tpathletics.entity.Product;
import com.example.tpathletics.repository.CartRepository;
import com.example.tpathletics.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    /*
    RestAPI to communicate the information from the database to the Angular application for the products that are added to the cart
     */

    private final CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @RequestBody CartItemRequest cartItemRequest) {
        Long productId = cartItemRequest.getProductId();
        String size = cartItemRequest.getSize(); // Get the size from the request
        Cart cart = cartService.addToCart(cartId, productId, size); // Pass the size to the service layer
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/new")
    public ResponseEntity<Cart> createCart() {
        Cart newCart = new Cart();
        Cart savedCart = cartRepository.save(newCart); // Use the service to create a new cart
        return ResponseEntity.ok(savedCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<Product>> getCartItems(@PathVariable Long cartId) {
        List<Product> items = cartService.getCartItems(cartId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok().build();
    }
}
