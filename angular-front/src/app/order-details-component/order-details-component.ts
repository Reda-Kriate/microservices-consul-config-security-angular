import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {JsonPipe, NgIf} from '@angular/common';

@Component({
  selector: 'app-order-details-component',
  imports: [
    JsonPipe,
    NgIf
  ],
  templateUrl: './order-details-component.html',
  styleUrl: './order-details-component.css'
})
export class OrderDetailsComponent implements OnInit{
  orderDetails:any;
  customerId: number;

  constructor(private http:HttpClient,private activateRoute:ActivatedRoute) {
    this.customerId=this.activateRoute.snapshot.params["customerId"];
  }
 ngOnInit() {
    this.http.get("http://localhost:9999/order-service/fullOrders/"+this.customerId).subscribe({
      next:(data)=>{
        this.orderDetails=data;
      },
      error:(err)=>{}
    })

 }
}
