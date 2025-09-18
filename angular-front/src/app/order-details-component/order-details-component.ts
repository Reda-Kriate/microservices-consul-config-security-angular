import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {DecimalPipe, JsonPipe, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-order-details-component',
  imports: [
    JsonPipe,
    NgIf,
    NgForOf,
    DecimalPipe,
  ],
  templateUrl: './order-details-component.html',
  styleUrl: './order-details-component.css'
})
export class OrderDetailsComponent implements OnInit{
  orderDetails:any;
  orderId: number;

  constructor(private http:HttpClient,private activateRoute:ActivatedRoute) {
    this.orderId=this.activateRoute.snapshot.params["orderId"];
  }
 ngOnInit() {
    this.http.get("http://localhost:9999/order-service/fullOrders/"+this.orderId).subscribe({
      next:(data)=>{
        this.orderDetails=data;
      },
      error:(err)=>{
        console.log(err);
      }
    })

 }
}
