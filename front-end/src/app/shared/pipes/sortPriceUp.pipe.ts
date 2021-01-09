import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../interfaces';

@Pipe({
  name: 'sortPriceUp'
})
export class SortPriceUpPipe implements PipeTransform {
  transform(products: Product[], sort: boolean): Product[] {
    if (!sort) {
      return products
    }
    return products.sort((a, b) => {
      if (a.price > b.price) {
        return 1
      }
      if (a.price < b.price) {
        return -1
      }
      return 0
    })
  }
}
