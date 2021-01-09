import {Component, Input, OnInit, Output, ViewEncapsulation, EventEmitter, OnChanges} from '@angular/core';
import {formatCurrency} from '@angular/common';



@Component({
  selector: 'app-slider-price',
  templateUrl: './slider-price.component.html',
  styleUrls: ['./slider-price.component.sass'],
  encapsulation: ViewEncapsulation.None
})
export class SliderPriceComponent implements OnInit, OnChanges{


  @Input() maxPrice
  @Input() minPrice

  @Output () elementMin = new EventEmitter()
  @Output () elementMax = new EventEmitter()

  changeMinPrice(range: number[]) {
    this.elementMin.emit(range[0])
  }

  changeMaxPrice(range: number[]) {
    this.elementMax.emit(range[1])
  }

  someRange2config
  someRange2

  ngOnChanges(): void {
    this.someRange2 = [this.minPrice, this.maxPrice]
  }

  ngOnInit(): void {
    this.someRange2 = [this.minPrice, this.maxPrice] //значения ползунков
    this.someRange2config = {
      behaviour: 'drag',
      connect: true,
      margin: 5, // миним расстрояние между ползунками
      tooltips: [true, true], // значения на ползунках
       // NOTE: overwritten by [limit]="10"
      range: { // диапазон шкалы
        min: 1,
        max: this.maxPrice*1.2,
      },
      step:5,
      // отображение шкалы
      // pips: {
      //   mode: 'steps',
      //   density: 5
      // }
    }
  }
}
