import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl= 'http://localhost:9990/api/products';

  constructor(private http:HttpClient) { }

  //Retrieve the list of all the products from the database using the RestAPI in the Product Controller
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }


}
