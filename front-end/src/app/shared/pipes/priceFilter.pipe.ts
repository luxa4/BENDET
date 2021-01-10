import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../interfaces';



@Pipe({name:'priceFilter'})
export class PriceFilterPipe implements PipeTransform {
  transform(product: Product[], minPrice: number, maxPrice: number): Product[] {
    if (minPrice == 7663 || maxPrice == 237405) {
      return product
    }

    return product.filter( pr => {
      return pr.price >= minPrice && pr.price <= maxPrice
    })
  }

}
