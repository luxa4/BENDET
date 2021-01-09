import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../interfaces';

@Pipe({
  name: 'sortNameDown'
})
export class SortNameDownPipe implements PipeTransform {
  transform(products: Product[], sort: boolean): Product[] {
    if (!sort) {
      return products
    }
    return products.sort((a, b) => {
      if (a.name < b.name) {
        return 1
      }
      if (a.name > b.name) {
        return -1
      }
      return 0
    })
  }
}
