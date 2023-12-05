package com.example.tpathletics.service;

import com.example.tpathletics.entity.Cart;
import com.example.tpathletics.entity.CartItem;
import com.example.tpathletics.entity.Product;
import com.example.tpathletics.repository.CartRepository;
import com.example.tpathletics.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    /*
    Adds an item to the cart and also generates a new item to the cart table in the database
     */
    @Transactional
    public Cart addToCart(Long cartId, Long productId, String size) {
        Cart cart = cartRepository.findById(cartId).orElseGet(Cart::new);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setSize(size);

        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }


    /*
    Gets all the products that are added to the cart and turns it into a list
     */
    public List<Product> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getItems().stream().map(CartItem::getProduct).collect(Collectors.toList());
    }


    /*
    Deletes the cart items the database
     */
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
