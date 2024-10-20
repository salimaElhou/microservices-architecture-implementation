import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  bill : any;
  customerId !:number;
  total!:number;
  constructor(private  http:HttpClient , private  router : Router,private route :ActivatedRoute) {
    this.customerId=route.snapshot.params['customerId']
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/BILLING-SERVICE/fullBill/"+this.customerId).subscribe(
      { next:(data)=>{
          this.bill = data;
          this.total=0
          for (let i = 0; i < this.bill.productItems.length; i++) {
            this.total = this.total + this.bill.productItems[i].quantity*this.bill.productItems[i].price
          }
          this.bill.productItems
        },
        error : (err)=>{}
      });
  }


}

