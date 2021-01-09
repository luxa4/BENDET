import {BrowserModule} from '@angular/platform-browser';
import {NgxSliderModule} from '@angular-slider/ngx-slider';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';

import {NgModule, Provider} from '@angular/core';
import {QuillModule} from 'ngx-quill';
import {AppComponent} from './app.component';
import {MainLayoutComponent} from './shared/components/main-layout/main-layout.component';
import {HomePageComponent} from './home-page/home-page.component';
import {ProductPageComponent} from './product-page/product-page.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {registerLocaleData} from '@angular/common';
import ruLocale from '@angular/common/locales/ru';
import {TestInterceptor} from './shared/test.interceptor';
import {FilterPipe} from './shared/pipes/filter.pipe';
import {BrandFilterPipe} from './shared/pipes/brandFilter.pipe';
import {TypeFilterPipe} from './shared/pipes/typeFilter.pipe';
import {SortNameUpPipe} from './shared/pipes/sortNameUp.pipe';
import {SortNameDownPipe} from './shared/pipes/sortNameDown.pipe';
import {SortPriceDownPipe} from './shared/pipes/sortPriceDown.pipe';
import {SortPriceUpPipe} from './shared/pipes/sortPriceUp.pipe';
import { SliderPriceComponent } from './shared/slider-price/slider-price.component';
import { NouisliderModule } from 'ng2-nouislider';
import {PriceFilterPipe} from './shared/pipes/priceFilter.pipe';

registerLocaleData(ruLocale, 'ru')

const TEST_INTERCEPTOR: Provider={
  provide: HTTP_INTERCEPTORS,
  useClass: TestInterceptor,
  multi: true
}

@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    HomePageComponent,
    ProductPageComponent,
    FilterPipe,
    BrandFilterPipe,
    TypeFilterPipe,
    SortNameUpPipe,
    SortNameDownPipe,
    SortPriceUpPipe,
    SortPriceDownPipe,
    PriceFilterPipe,
    SliderPriceComponent
  ],
  imports: [
    QuillModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NouisliderModule
  ],
  providers: [TEST_INTERCEPTOR],
  bootstrap: [AppComponent]
})
export class AppModule {}
