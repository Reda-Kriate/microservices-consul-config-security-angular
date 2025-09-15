import { Routes } from '@angular/router';
import {ProductsComponent} from './products-component/products-component';
import {CustomersComponent} from './customers-component/customers-component';

export const routes: Routes = [
  {path:"products" , component:ProductsComponent},
  {path:"customers" , component:CustomersComponent}
];
