import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../shared/product.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Product} from '../../../shared/interfaces';
import {FormControl, FormGroup} from '@angular/forms';
import {switchMap} from 'rxjs/operators';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-edit-page',
  templateUrl: './edit-page.component.html',
  styleUrls: ['./edit-page.component.sass']
})
export class EditPageComponent implements OnInit {

  updateSub: Subscription

  form:FormGroup

  public product: Product

  id

  constructor(
    private prodService: ProductService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap( (params:Params) => {
      this.id = params['id']
      return this.prodService.getProductById(this.id)
    })).subscribe( (pr:Product) => {
      this.product = pr
      console.log(pr.name)
      this.form = new FormGroup({
        name: new FormControl(pr.name),
        type: new FormControl(pr.category.name),
        brand: new FormControl(pr.producer.name),
        width: new FormControl(pr.width),
        length: new FormControl(pr.length),
        height: new FormControl(pr.height),
        weight: new FormControl(pr.weight),
        price: new FormControl(pr.price),
        status: new FormControl(pr.status),
        description: new FormControl(pr.description),
        img1: new FormControl(pr.imageUrl1),
        img2: new FormControl(pr.imageUrl2),
        img3: new FormControl(pr.imageUrl3),
        img4: new FormControl(pr.imageUrl4),
        img5: new FormControl(pr.imageUrl5)
      })
    })
  }

  updateProduct() {
    const updateProduct: Product = {
      id: this.product.id,
      name: this.form.value.name,
      category: this.form.value.type,
      producer: this.form.value.producer,
      width: this.form.value.width,
      length: this.form.value.length,
      height: this.form.value.height,
      price: this.form.value.price,
      status: this.form.value.status,
      weight: this.form.value.weight,
      description: this.form.value.description,
      imageUrl1: this.form.value.img1,
      imageUrl2: this.form.value.img2,
      imageUrl3: this.form.value.img3,
      imageUrl4: this.form.value.img4,
      imageUrl5: this.form.value.img5
    }
    console.log(updateProduct);
    this.updateSub = this.prodService.updateProduct(updateProduct.id, updateProduct).subscribe( () => {
      this.router.navigate(['admin/list'])
    })

  }


}
