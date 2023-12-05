import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/cart/cart.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products:Product[] = [];
  filteredProducts:Product[] = [];
  sortOrder:string ="";


  constructor(
    private productService: ProductService,
    private cartService:CartService,
    private snackbar:MatSnackBar){}

  ngOnInit(): void {
    this.productService.getProducts().subscribe(data => {
      console.log('Products:', data);
      this.products = data;
      this.filteredProducts = data;
      console.log('Initial filteredProducts:', this.filteredProducts);
    });
  }


  addToCart(product: Product, size : String): void {
    console.log('Attempting to add product to cart:', product.name);

    const cartId = sessionStorage.getItem('cartId');
    console.log('Retrieved cartId from sessionStorage:', cartId);
    const productId = product.id;


    if (cartId && !isNaN(Number(cartId))) {
      console.log('Adding product to an existing cart:', cartId);
      this.cartService.addToCart(+cartId, productId,size).subscribe({
        next: (cart) => {
          console.log('Product successfully added to cart:', cart);
          this.snackbar.open("Product added to cart!", "", {
            duration: 3000,
            horizontalPosition: 'right',
            verticalPosition: 'top'
          });
        },
        error: (error) => {
          console.error('Error adding product to cart:', error);
        }
      });
    } else {
      console.log('No valid cartId found, creating a new cart');
      this.cartService.createCart().subscribe({
        next: (cart) => {
          if (cart && cart.id !== undefined) {
            console.log('New cart created:', cart);
            sessionStorage.setItem('cartId', String(cart.id));
            console.log('Stored new cartId in sessionStorage:', cart.id);
            // Add the product to the new cart
            this.cartService.addToCart(cart.id, productId,size).subscribe({
              next: (updatedCart) => {
                console.log('Product successfully added to new cart:', updatedCart);
                this.snackbar.open("Product added to cart!", "", {
                  duration: 3000,
                  horizontalPosition: 'right',
                  verticalPosition: 'top'
                });
              },
              error: (error) => {
                console.error('Error adding product to new cart:', error);
              }
            });
          } else {
            console.error('Cart ID not found in the response');
          }
        },
        error: (error) => {
          console.error('Error creating a new cart:', error);
        }
      });
    }
  }

applyFilter(event: Event):void{
  let searchTerm = (event.target as HTMLInputElement).value;
  searchTerm = searchTerm.toLowerCase();

  this.filteredProducts=this.products.filter(
    product=>product.name.toLowerCase().includes(searchTerm))

  this.sortProducts(this.sortOrder);

}

sortProducts(sortValue: string){
  this.sortOrder=sortValue;

  if(this.sortOrder ==="priceLowHigh"){
    this.filteredProducts.sort((a,b)=>a.price - b.price);
  }else if(this.sortOrder==="priceHighLow"){
    this.filteredProducts.sort((a,b) => b.price - a.price);
  }
}

  // getProductImageUrl(image_url: string): string {
  //   return `http://localhost:9990/store/assets/images/${image_url}`;
  // }

}
