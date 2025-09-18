import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-order-component',
  imports: [
    NgForOf,
    NgIf,
    DatePipe
  ],
  templateUrl: './order-component.html',
  styleUrl: './order-component.css'
})
export class OrderComponent implements OnInit{
  orders:any;
  customerId!:number;
  constructor(private http:HttpClient,
              private router:Router,
              private activateRoute:ActivatedRoute) {
    this.customerId=this.activateRoute.snapshot.params["customerId"];
  }

  ngOnInit() {
    this.http.get("http://localhost:9999/order-service/orders/search/byCustomerId?customerId="+this.customerId).subscribe({
      next:(data)=>{
        this.orders=data;
      },
      error:(err)=>{}
    })
  }

  getOrderDetail(order: any) {
    this.router.navigateByUrl("/orderDetails/"+order.id)
  }
  
}
