import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from '../../../shared/product.service';
import {Product} from '../../../shared/interfaces';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-list-page',
  templateUrl: './list-page.component.html',
  styleUrls: ['./list-page.component.sass']
})
export class ListPageComponent implements OnInit {
  public products: Product[]

  prodSub: Subscription
  delSub: Subscription

  constructor(
    private prodService: ProductService,
    private router: Router) { }

  ngOnInit(): void {
    this.prodSub = this.prodService.getProducts(9).subscribe( pr => {
      this.products = pr
    })
  }

  deleteProduct(id: number) {
    this.delSub = this.prodService.remove(id).subscribe( () => {
      this.products = this.products.filter(pr => pr.id !== id)
    })
  }


  // ngOnDestroy(): void {
  //   this.prodSub.unsubscribe()
  //   this.delSub.unsubscribe()
  // }
}
