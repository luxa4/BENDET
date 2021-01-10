import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../interfaces';

@Pipe({name: 'typeFilter'})
export class TypeFilterPipe implements PipeTransform {
  transform(product: Product[], type: String): Product[] {
    if (!type.trim()) {
      return product
    }
    return product.filter( pr => {
      return pr.category.name.toLowerCase() === type.toLowerCase()
    })
  }

}
