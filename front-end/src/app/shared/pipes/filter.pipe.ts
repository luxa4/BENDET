import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../interfaces';

@Pipe({
  name: 'search'
})
export class FilterPipe implements PipeTransform {
  transform(products: Product[], search: String): Product[] {
    if (!search.trim()) {
      return products
    }
    return products.filter( pr => {
        return pr.name.toLowerCase().indexOf(search.toLowerCase()) !==-1
    }
    )
  }
}
