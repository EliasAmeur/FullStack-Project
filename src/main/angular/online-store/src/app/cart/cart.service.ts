import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { Cart } from '../models/cart';
import {CartItem} from "../models/CartItem";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private apiUrl="http://localhost:9990/api/cart";
  private apiCheckoutUrl=environment.apiUrl+"/checkout";

  constructor(private http:HttpClient) {
  }

  //Calls the RestAPI from cart controller to create a new cart in the database
  createCart(): Observable<Cart> {
    return this.http.post<Cart>(`${this.apiUrl}/new`, {});
  }

  //Calls the RestAPI from cart controller to add products to a cart in the database
  addToCart(cartId: number, productId: number, size : String): Observable<Cart> {
    const requestBody = { productId: productId , size:size};
    return this.http.post<Cart>(`${this.apiUrl}/${cartId}/add`, requestBody);
  }

  //Calls the RestAPI from cart controller to to get all the products that are added to the cart
  getCartItems(cartId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/${cartId}`);
  }

  //Calls the RestAPI from cart controller to remove all the items from the cart and the database
  clearCart(cartId: number): Observable<void> {

    return this.http.delete<void>(`${this.apiUrl}/${cartId}/clear`);
  }

  //Technically would call or redirect to a payment processing page in a real world scenario, currently simply redirect to the main store page
  checkout(products:Product[]):Observable<void>{
    return this.http.post<void>(this.apiCheckoutUrl, products);
  }

}
