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
  public producers:  String[]=[]
  public categories: Object[]=[]
  public prices: number[] =[]
  public newArr: Product[]
  prodSub: Subscription
  prod1Sub: Subscription

  srcTmp: string
  string: string = ''
  producer: string = ''
  category: string = ''
  minPrice
  maxPrice
  sortPriceUp: boolean
  sortPriceDown: boolean
  sortNameUp: boolean
  sortNameDown: boolean
  loadMoreFlag = true
  loadMoreCount = 0
  totalPages: number = 0
  typeLoadMore: String = 'allProduct'
  id_category:number

  constructor(private prodService:ProductService) { }

  defaultSets():Promise<any> {
    return new Promise( (resolve, reject) => {
      this.prodSub = this.prodService.getProducts(this.loadMoreCount).subscribe(products => {
        this.totalPages = products.totalPages
        this.products = products.content
        resolve()
      })
    })

  }

  getProducers(id_category: number) {
    this.prodService.getProducersByCategory(id_category).subscribe( resp => {
      this.producers = resp
                        .map( resp => resp.name)
                        .filter ( (cat, i, cats) => cats.indexOf(cat) === i)
    })
  }

  ngOnInit(): void {
    this.defaultSets().then( () => {
      this.prodService.getCategories().subscribe( resp => {
        this.categories = resp
      })
      this.prices = this.products.map( pr => pr.price)
      this.minPrice = Math.min(...this.prices)
      this.maxPrice = Math.max(...this.prices)
    })

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
    this.defaultSets()
    this.producer = ''
    this.category = ''
    this.string = ''
    this.minPrice =  Math.min(...this.prices)
    this.maxPrice =  Math.max(...this.prices)
    this.typeLoadMore = 'allProduct'
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

  loadMore(typeLoadMore: String) {
    this.loadMoreCount++

    switch (typeLoadMore) {
      case "allProduct":
        console.log(`Случай - allProducts`);
        this.prod1Sub = this.prodService.getProducts(this.loadMoreCount).subscribe( resp => {
          this.products.push(...resp.content)
          console.log("контент", resp.content)
          console.log("с пушем",this.products)
          })
          break;
      case "byCategory":
          console.log(`Случай - byCategory`);
          this.prod1Sub = this.prodService.getProductsByCategory(this.id_category, this.loadMoreCount).subscribe( resp => {
            this.products.push(...resp.content)
            console.log("контент", resp.content)
            console.log("с пушем", this.products)
          })
            break;
    }

    console.log(this.loadMoreCount, this.totalPages)
    if (this.loadMoreCount+1 >= this.totalPages) {
      this.loadMoreFlag = false
      this.loadMoreCount = 0
    }
  }

  showProductByCategory(id_category: number) {
    this.loadMoreFlag = true
    this.prodService.getProductsByCategory(id_category, this.loadMoreCount).subscribe( products => {
      this.products = products.content
      this.totalPages = products.totalPages
    })
    this.getProducers(id_category)

  }
}
