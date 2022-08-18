import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { removeTokened } from 'src/app/store/reducers.component';
import { store } from 'src/app/store/store.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isCollapsed = false;
  constructor(private router:Router) { }
  logout() {
    /*  localStorage.removeItem("token") */
    console.log("前")
    store.dispatch(removeTokened());
    this.router.navigate(['/login'])
  }
  ngOnInit(): void {
  }

}
