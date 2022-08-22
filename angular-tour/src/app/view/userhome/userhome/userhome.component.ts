import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { removeTokened } from 'src/app/store/reducers.component';
import { store } from 'src/app/store/store.component';
@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {
  isCollapsed = false;
  constructor(private router:Router) { }
  logout() {
    store.dispatch(removeTokened());
    this.router.navigate(['/ulogin'])
   }
  ngOnInit(): void {
  }

}


