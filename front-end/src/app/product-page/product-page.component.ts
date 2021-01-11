import { Component, OnInit } from '@angular/core';
import {ProductService} from '../shared/product.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../shared/interfaces';

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.sass']
})
export class ProductPageComponent implements OnInit {

  productsId:number
  product:Product
  count: number=1
  showParams= false
  showDescription=false

  constructor(private prodService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.productsId = params['id'])

    this.prodService.getProductById(this.productsId).subscribe( pr => this.product = pr)
  }

  addToCart(id: number) {

  }
}
