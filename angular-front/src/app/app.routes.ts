import { Routes } from '@angular/router';
import {ProductsComponent} from './products-component/products-component';
import {CustomersComponent} from './customers-component/customers-component';
import {OrderComponent} from './order-component/order-component';
import {OrderDetailsComponent} from './order-details-component/order-details-component';

export const routes: Routes = [
  {path:"products" , component:ProductsComponent},
  {path:"customers" , component:CustomersComponent},
  {path:"orders/:customerId" , component:OrderComponent},
  {path:"orderDetails/:orderId" , component:OrderDetailsComponent},
];
