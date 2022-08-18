import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { store } from 'src/app/store/store.component';
import { setTokened } from 'src/app/store/reducers.component';
@Component({
  selector: 'app-userlogin',
  templateUrl: './userlogin.component.html',
  styleUrls: ['./userlogin.component.css']
})
export class UserloginComponent implements OnInit {

  validateForm!: FormGroup;
 
  constructor(private fb: FormBuilder,private service:UserService,private message:NzMessageService,private router:Router,private location:Location) {
    
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(!this.validateForm.valid){
      console.log('验证失败！')
      return 
    }
    const {name,password}=this.validateForm.value;
    let logindto={name,password}
    this.service.login(logindto).subscribe((res:any)=>{
      console.log(res.message)
      console.log(res)
      if (res.code != 1006) {
        this.message.error(res.message)
      }
      else {
        const token=store.dispatch(setTokened(res.data.token));
        this.message.success("登录成功!")
        this.router.navigate(['/uhome'])
        console.log(res.data)
      }
    })


  }
  back(){
    this.router.navigate(['/first'])
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [ '马克44', [ Validators.required ] ],
      password: [  '马克44', [ Validators.required ,Validators.minLength(3),Validators.maxLength(9)]],

    });
   
  }
}


