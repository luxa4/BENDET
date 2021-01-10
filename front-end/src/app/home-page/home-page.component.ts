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

  private productOnPage = 9

  private allProducts: Product[]
  public products:  Product[]
  public productsInCategory: Product[]
  public productsFiltered: Product[]
  public allProducers:  String[]=[]
  public producers:  String[]=[]
  public categories: String[]=[]
  public prices: number[] =[]
  public newArr: Product[]
  prodSub: Subscription


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
    this.productsFiltered = this.allProducts
    this.products = this.productsFiltered.filter( (v, i, arr) => i < this.productOnPage)
    this.totalPages = this.getTotalPages(this.allProducts.length, this.productOnPage)
    this.producer = ''
    this.category = ''
    this.string = ''
    this.minPrice =  Math.min(...this.prices)
    this.maxPrice =  Math.max(...this.prices)
    this.loadMoreFlag = true
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
    this.products = this.productsFiltered

    this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
    if (this.totalPages >=2) {
      this.loadMoreFlag = true
    }
    this.products = this.productsFiltered.filter( (v, i, arr) => i< this.productOnPage)
  }

  changeProducer(producer: string) {
    this.loadMoreCount = 0
    if (!producer.trim() && this.category == '') {
      this.productsFiltered = this.allProducts
      this.products = this.productsFiltered
      this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
      if (this.totalPages >= 2) {
        this.loadMoreFlag = true
      }
      console.log(`Не выбрана Категория и стоит ВСЕ - товаров ${this.productsFiltered.length} , страниц - ${this.totalPages}`)
      this.products = this.productsFiltered
        .filter((v, i, arr) => i < this.productOnPage)
    } else if (this.category == '') {
      this.productsFiltered = this.allProducts.filter( pr => pr.producer.name === producer)
      this.products = this.productsFiltered
      this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
      if (this.totalPages >=2) {
        this.loadMoreFlag = true
      } else {
        this.loadMoreFlag = false
      }
      console.log(`Не выбрана Категория и выбран производитель - товаров ${this.productsFiltered.length} , страниц - ${this.totalPages}`)
      this.products = this.products
        .filter( (v, i, arr) => i< this.productOnPage)
    } else if (!producer.trim()) {
      this.productsFiltered = this.allProducts
        .filter( pr => pr.category.name === this.category)
      this.products = this.productsFiltered
      this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
      if (this.totalPages >= 2) {
        this.loadMoreFlag = true
      } else {
        this.loadMoreFlag = false
      }
      console.log(`Выбрана Категория и стоит ВСЕ - товаров ${this.productsFiltered.length} , страниц - ${this.totalPages}`)
      this.products = this.productsFiltered
        .filter( (v, i, arr) => i< this.productOnPage)
    } else {
      this.productsFiltered = this.allProducts.filter( pr => pr.category.name === this.category)
      this.productsFiltered = this.productsFiltered.filter( pr => pr.producer.name === producer)
      this.products = this.productsFiltered
      this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
      if (this.totalPages >=2) {
        this.loadMoreFlag = true
      } else {
        this.loadMoreFlag = false
      }
      console.log(`Выбрана Категория и выбран производ - товаров ${this.productsFiltered.length} , страниц - ${this.totalPages}`)
      this.products = this.products
        .filter( (v, i, arr) => i< this.productOnPage)
    }
  }

  toSortPriceUp() {

  }

  toSortNameUp() {

  }

  toSortPriceDown() {

  }

  toSortNameDown() {

  }
}
