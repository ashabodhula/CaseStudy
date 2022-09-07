import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reader-dashboard',
  templateUrl: './reader-dashboard.component.html',
  styleUrls: ['./reader-dashboard.component.css']
})
export class ReaderDashboardComponent implements OnInit {

  constructor(public route :Router) { }

  ngOnInit(): void {
  }
  logout() {
    localStorage.removeItem("token");
    this.route.navigate(['/']);
  }

}
