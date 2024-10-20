import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'],
})
export class CustomersComponent implements OnInit {
  customer: any = { _embedded: { customers: [] } };

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers?projection=p1").subscribe({
      next: (data) => {
        this.customer = data;
      },
      error: (err) => {
        console.error('Error fetching customers:', err);
        this.customer = { _embedded: { customers: [] } };
      },
    });
  }

  getBill(c: any) {
    this.router.navigate(['/bill', c.id]);
  }


}
