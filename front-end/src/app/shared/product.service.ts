import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Product} from './interfaces';
import {catchError, tap} from 'rxjs/operators';
import {log} from 'util';

@Injectable({providedIn: 'root'})
export class ProductService implements OnInit{

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8080/api/products')
  }

  getProductsWithPag(page: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/productsWithPagination/${page}`)
      .pipe(
        tap(resp => console.log(resp))
      )

  }

  getProductById(id: number):Observable<Product> {
    return this.http.get<Product>(`http://localhost:8080/api/products/${id}`)
  }

  remove(id: number) {
    return this.http.delete(`http://localhost:8080/api/products/delete/${id}`).pipe(catchError(err => {
      return throwError(err)
      })
    )

  }

  updateProduct(id: number, product: Product) {
    return this.http.put(`http://localhost:8080/api/products/update/${id}`, product)
  }
}
