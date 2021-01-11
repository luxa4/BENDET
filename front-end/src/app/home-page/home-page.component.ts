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


  private productOnPage = 9

  private allProducts: Product[]
  public products:  Product[]
  public productsFiltered: Product[]
  public allProducers:  String[]=[]
  public producers:  String[]=[]
  public categories: String[]=[]
  public prices: number[] =[]
  prodSub: Subscription


  srcTmp: string
  string: string = ''
  producer: string = ''
  category: string = ''
  minPrice
  maxPrice
  sortPriceUp = false
  sortPriceDown = false
  sortNameUp = false
  sortNameDown = false
  loadMoreFlag = true
  loadMoreCount = 0
  totalPages: number = 0

  constructor(private prodService:ProductService) { }

  ngOnInit(): void {
    this.prodSub = this.prodService.getProducts().subscribe(products => {
      this.allProducts = products
      this.productsFiltered = this.allProducts
      this.products = this.allProducts.filter( (v, i, arr) => i < this.productOnPage)

      this.producers = this.allProducers = products
        .map(products => products.producer.name)
        .filter( (v, i, arr) => arr.indexOf(v) === i)
      console.log(`OnInit allProducers : ${this.allProducers}`)

      this.categories = products
        .map(products => products.category.name)
        .filter( (v, i, arr) => arr.indexOf(v) === i)
      console.log(`OnInit categories: ${this.categories}`)

      this.prices = this.allProducts.map( products => products.price)
      this.minPrice = Math.min(...this.prices)
      this.maxPrice = Math.max(...this.prices)
      console.log("Mix Price",this.minPrice)
      console.log("Max Price",this.maxPrice)
      this.totalPages = this.getTotalPages(this.allProducts.length, this.productOnPage)
    })
  }

  getTotalPages(countProducts: number, productsOnPage) {
    return  Math.ceil(countProducts/productsOnPage)
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
    this.producer = ''
    this.string = ''
    this.minPrice =  Math.min(...this.prices)
    this.maxPrice =  Math.max(...this.prices)
    this.loadMoreFlag = true
    this.sortPriceUp = false
    this.sortPriceDown = false
    this.sortNameUp = false
    this.sortNameDown = false
    this.category =='' ? this.productsFiltered = this.allProducts :  this.productsFiltered = this.allProducts.filter( pr => pr.category.name === this.category)
    this.setsFilter(this.productsFiltered)
  }

  changePriceMin(min:number) {
    this.minPrice= min
  }

  changePriceMax(max:number) {
    this.maxPrice= max
  }

  ngOnDestroy(): void {
    this.prodSub.unsubscribe()

  }

  sortByPriceAndName(productsFiltered: Product[]):Product[] {
    switch (true) {
      case this.sortPriceUp:
        return productsFiltered.sort((a, b) => {
          if (a.price > b.price) {
            return 1
          }
          if (a.price < b.price) {
            return -1
          }
          return 0
        })
      case this.sortPriceDown:
        return productsFiltered.sort((a, b) => {
          if (a.price < b.price) {
            return 1
          }
          if (a.price > b.price) {
            return -1
          }
          return 0
        })
      case this.sortNameUp:
        return productsFiltered.sort((a, b) => {
          if (a.name > b.name) {
            return 1
          }
          if (a.name < b.name) {
            return -1
          }
          return 0
        })
      case this.sortNameDown:
        return productsFiltered.sort((a, b) => {
          if (a.name < b.name) {
            return 1
          }
          if (a.name > b.name) {
            return -1
          }
          return 0
        })
      default:
        return productsFiltered
    }
  }

  loadMore() {
    this.loadMoreCount++
    var temp = this.productsFiltered
          .filter( (v, i, arr) => i >= (this.loadMoreCount * this.productOnPage) &&  i < (this.loadMoreCount * this.productOnPage+this.productOnPage))
    this.products.push(...temp)
    console.log("контент", temp)
    console.log("с пушем",this.products)

    console.log(this.loadMoreCount, this.totalPages)
    if (this.loadMoreCount+1 >= this.totalPages) {
      this.loadMoreFlag = false
      this.loadMoreCount = 0
    }
  }

  showProductByCategory(cat: String) {
    this.productsFiltered = this.allProducts.filter( pr => pr.category.name === cat)
    this.setsFilter(this.productsFiltered)
  }

  applyFilter() {
    this.loadMoreCount = 0
    if (!this.producer.trim() && this.category == '') {
      this.productsFiltered = this.allProducts
        .filter(pr => pr.price >= this.minPrice && pr.price <= this.maxPrice)


      this.setsFilter(this.productsFiltered)

    } else if (this.category == '') {
      this.productsFiltered = this.allProducts
        .filter(pr => pr.price >= this.minPrice && pr.price <= this.maxPrice)
        .filter( pr => pr.producer.name === this.producer)
      this.setsFilter(this.productsFiltered)

    } else if (!this.producer.trim()) {
      this.productsFiltered = this.allProducts
        .filter(pr => pr.price >= this.minPrice && pr.price <= this.maxPrice)
        .filter( pr => pr.category.name === this.category)
      this.setsFilter(this.productsFiltered)

    } else {
      this.productsFiltered = this.allProducts
        .filter(pr => pr.price >= this.minPrice && pr.price <= this.maxPrice)
        .filter( pr => pr.category.name === this.category)
        .filter( pr => pr.producer.name === this.producer)
      this.setsFilter(this.productsFiltered)
    }
  }

  private setsFilter(productsFiltered: Product[]) {
    productsFiltered = this.sortByPriceAndName(productsFiltered)
    this.products = productsFiltered
    this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
    this.totalPages >=2 ? this.loadMoreFlag = true : this.loadMoreFlag = false
    this.products = this.products.filter( (v, i, arr) => i< this.productOnPage)
  }


}
