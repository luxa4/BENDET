import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ProductService} from '../shared/product.service';
import {Product} from '../shared/interfaces';
import {Subscription} from 'rxjs';
import {log} from 'util';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.sass']
})
export class HomePageComponent implements OnInit, OnDestroy {

  @ViewChild('addProduct') el:ElementRef

  public products:  Product[]
  public brands:  String[]=[]
  public types: String[]=[]
  public prices: number[] =[]
  public newArr: Product[]
  prodSub: Subscription
  prod1Sub: Subscription

  srcTmp: string
  string: string = ''
  brand: string = ''
  type: string = ''
  minPrice
  maxPrice
  sortPriceUp: boolean
  sortPriceDown: boolean
  sortNameUp: boolean
  sortNameDown: boolean
  loadMoreFlag = true
  loadMoreCount = 0
  totalPages: number = 0


  constructor(private prodService:ProductService) { }

  ngOnInit(): void {
      this.prodSub = this.prodService.getProductsWithPag(this.loadMoreCount).subscribe(products => {
      this.totalPages = products.totalPages
      this.products = products.content
      this.brands = this.products.map( pr => pr.brand)
      this.types = this.products.map( pr => pr.type)
      this.prices = this.products.map( pr => pr.price)
      this.brands = this.brands.filter((v, i, a) => a.indexOf(v) === i)
      this.types = this.types.filter((v, i, a) => a.indexOf(v) === i)
      this.minPrice = Math.min(...this.prices)
      this.maxPrice = Math.max(...this.prices)

    })
    // this.prod1Sub = this.prodService.getProductsWithPag(this.loadMoreCount).subscribe( resp => this.totalPages = resp.totalPages)
  }

  changeSrc(product:Product, inFocus:boolean) {
    if (inFocus) {
      this.srcTmp = product.imageUrl1
      product.imageUrl1 = product.imageUrl2
    } else {
      product.imageUrl1 = this.srcTmp
    }
  }

  resetFilters() {
    this.brand = ''
    this.type = ''
    this.string = ''
    this.minPrice =  Math.min(...this.prices)
    this.maxPrice =  Math.max(...this.prices)
    console.log(this.minPrice)
    console.log(this.maxPrice)
  }

  changePriceMin(min:number) {
    this.minPrice= min
  }

  changePriceMax(max:number) {
    this.maxPrice= max
  }

  ngOnDestroy(): void {
    this.prodSub.unsubscribe()
    this.prod1Sub.unsubscribe()
  }

  loadMore() {
    this.loadMoreCount++
    if (this.loadMoreCount+1 >= this.totalPages) {
      this.loadMoreFlag = false
    }

    this.prod1Sub = this.prodService.getProductsWithPag(this.loadMoreCount).subscribe( resp => {
      this.products.push(...resp.content)
      console.log("контент", resp.content);
      console.log("с пушем",this.products);
      resp.pageable.offset
    })

    console.log(this.loadMoreCount, this.totalPages)


  }
}
