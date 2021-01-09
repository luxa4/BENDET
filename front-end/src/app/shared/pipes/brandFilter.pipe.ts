import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../interfaces';

@Pipe({name: 'brandFilter'})
export class BrandFilterPipe implements PipeTransform {
  transform(product: Product[], brand: String): Product[] {
    if (!brand.trim()) {
      return product
    }
    return product.filter( pr => {
      return pr.brand.toLowerCase() === brand.toLowerCase()
    })
  }

}
