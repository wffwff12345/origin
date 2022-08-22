import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { store } from './store/store.component';
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router:Router){
  }
  state:any;
  canActivate(): boolean {
    console.log('路由守卫');
/*     const token=localStorage.getItem("token");*/ 
    this.state = store.getState();
    const token = this.state.token;
    console.log('token.payload');
    console.log(typeof (token));
    console.log(token.payload);
    console.log('token');
    console.log(typeof (token));
    console.log(token);
    if (token.payload ) {
      return true;
    }else{
      this.router.navigate(['/login']);
      return false;
  }
  
}}


