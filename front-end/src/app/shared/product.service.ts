import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Product} from './interfaces';
import {catchError, tap} from 'rxjs/operators';
import {log} from 'util';

@Injectable({providedIn: 'root'})
export class ProductService implements OnInit{

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
  }

  getCategories(): Observable<any> {
    return this.http.get(`http://localhost:8080/api/categories`)
      .pipe(
        tap(resp => console.log(resp))
      )
  }

  getProducersByCategory(id_category: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/producers/`, {params: new HttpParams().set('id_category', `${id_category}`)})
      .pipe(
        tap(resp => console.log(resp))
      )
  }

  getPrices(): Observable<any> {
    return this.http.get(`http://localhost:8080/api/categories`)
      .pipe(
        tap(resp => console.log(resp))
      )
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8080/api/products`)
  }

  getProductsByCategory(id_category: number, page: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/products/${id_category}/${page}`)
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
