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
    this.products = this.allProducts.filter( (v, i, arr) => i< this.productOnPage)
    this.totalPages = this.getTotalPages(this.allProducts.length, this.productOnPage)
    this.producer = ''
    this.category = ''
    this.string = ''
    this.minPrice =  Math.min(...this.prices)
    this.maxPrice =  Math.max(...this.prices)
    this.typeLoadMore = 'allProduct'
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

  loadMore(typeLoadMore: String) {
    this.loadMoreCount++

    switch (typeLoadMore) {
      case "allProduct":
        console.log(`Случай - allProducts`);
        var temp = this.allProducts
          .filter( (v, i, arr) => i >= (this.loadMoreCount * this.productOnPage) &&  i < (this.loadMoreCount * this.productOnPage+this.productOnPage))
        this.products.push(...temp)
        console.log("контент", temp)
        console.log("с пушем",this.products)
        break;

      case "byCategory":
        console.log(`Случай - byCategory`);
        console.log("Produts при нажатии кнопки загрузить: ", this.products)
        console.log("loadMoreCount при нажатии кнопки загрузить: ", this.loadMoreCount)

        console.log("Всего в текущей категории",this.productsInCategory);
        var tempCat = this.productsInCategory
          .filter( (v, i, arr) => i >= (this.loadMoreCount * this.productOnPage) &&  i < (this.loadMoreCount * this.productOnPage+this.productOnPage))
        console.log("Отфильтрованный для добавления:", tempCat)
        console.log("Produts до добаления: ", this.products)
        this.products.push(...tempCat)
        console.log("Produts после добаления:", this.products)
        break;
    }

    console.log(this.loadMoreCount, this.totalPages)
    if (this.loadMoreCount+1 >= this.totalPages) {
      this.loadMoreFlag = false
      this.loadMoreCount = 0
    }
  }

  showProductByCategory(cat: String) {
    this.productsInCategory = this.allProducts.filter( pr => pr.category.name === cat)
    this.products = this.productsInCategory

    this.totalPages = this.getTotalPages(this.products.length, this.productOnPage)
    if (this.totalPages >=2) {
      this.loadMoreFlag = true
    }
    console.log('Categories pages', this.totalPages)
    this.products = this.productsInCategory.filter( (v, i, arr) => i< this.productOnPage)
    console.log('После перехода на категорию', this.products)
  }
}
