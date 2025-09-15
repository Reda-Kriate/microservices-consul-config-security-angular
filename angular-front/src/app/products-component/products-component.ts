import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-products-component',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './products-component.html',
  styleUrl: './products-component.css'
})
export class ProductsComponent implements OnInit{
  products:any;
  constructor(private http:HttpClient) {
  }
  ngOnInit():void{
    this.http.get("http://localhost:9999/inventory-service/products").subscribe({
      next : (data)=>{
        this.products=data;
        },
      error : err => {}
      }
    )
  }
}
