import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Product } from 'src/app/models/product';
import { CartItem } from 'src/app/models/CartItem';
import {Cart} from "../../models/cart";

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.css']
})
export class CartViewComponent implements OnInit{
  cartItems: Product[] = [];
  totalPrice: number = 0;
  cartId: number | null = null;
  size : String ="";

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.initializeOrRetrieveCart();
  }

  //If a user already had a cart created, it will retrieve the cart and load the products from that cart,
  //If not, it will create a new cart in the database for products to be stored in and save it to a session storage.
  initializeOrRetrieveCart(): void {
    const storedCartId = sessionStorage.getItem('cartId');
    if (storedCartId) {
      this.cartId = +storedCartId;
      this.loadCartItems();
    } else {
      this.cartService.createCart().subscribe({
        next: (cart) => {
          if (cart && cart.id !== undefined) {
            this.cartId = cart.id;
            sessionStorage.setItem('cartId', String(this.cartId));
            this.loadCartItems();
          } else {
            console.error('Cart ID not found in the response');
          }
        },
        error: (error) => console.error('Error creating a new cart:', error)
      });
    }
  }

  //Gets the list of all the products that were added to the cart
  loadCartItems(): void {
    if (this.cartId) {
      this.cartService.getCartItems(this.cartId).subscribe({
        next: (items) => {
          this.cartItems = items;
          this.totalPrice = this.getTotalPrice();

        },
        error: (error) => console.error('Error loading cart items:', error)
      });
    }
  }

//Calculates the total price
  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => {
      // Check if both item and item.product are defined and add price to total if so
      if (item && item.price) {
        return total + item.price;
      } else {
        // If item or item.product is undefined, or price is not a number, just return the current total
        return total;
      }
    }, 0);
  }

  //Removes the cart items from the page and the database
  clearCart(): void {
    if (this.cartId) {
      this.cartService.clearCart(this.cartId).subscribe({
        next: () => {
          this.cartItems = [];
          this.totalPrice = 0;
          sessionStorage.removeItem('cartId');
        },
        error: (error) => console.error('Error clearing cart:', error)
      });
    }
  }

  //Currently when clicking on the checkout button it removes the cart items from the database, In a real world scenario I would redirect the client
  //to an built-in payment processing app like Stripe to complete the transaction
  checkout(): void {
    if (this.cartId) {
      this.cartService.checkout(this.cartItems).subscribe({
        next: () => {
          // Handle successful checkout
          this.cartItems = [];
          this.totalPrice = 0;
          sessionStorage.removeItem('cartId');
        },
        error: (error) => console.error('Error during checkout:', error)
      });
    }
  }
}
