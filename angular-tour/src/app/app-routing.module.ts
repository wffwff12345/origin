import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './view/base/base.component';
import { LoginComponent } from './view/login/login.component';
import { RegisterComponent } from './view/register/register.component';
import { HomeComponent } from './view/home/home.component';
import { FirstComponent } from './view/first/first.component';
import { UserModule } from './view/user/user.module';
import { AuthGuard } from './auth.guard';
import { first } from 'rxjs';
import { ErrorComponent } from './error/error.component';
import { UserloginComponent } from './view/userlogin/userlogin.component';
import { UserhomeComponent } from './view/userhome/userhome/userhome.component';
import { OssuploadComponent } from './view/userhome/ossupload/ossupload.component';
//import { PicturelistComponent } from './view/userhome/picturelist/picturelist.component';
import { PicturelistComponent } from './view/userhome/picturelist/picturelist.component';
import { OssComponent } from './view/userhome/oss/oss.component';
import { NewslistComponent } from './view/userhome/newslist/newslist.component';
import { NewseditorComponent } from './view/userhome/newseditor/newseditor.component';
import { NewInfoComponent } from './view/userhome/new-info/new-info.component';
import { EditorComponent } from './view/userhome/editor/editor.component';
import { ContentlistComponent } from './view/userhome/new-info/contentlist/contentlist.component';
import { NewsComponent } from './view/news/news.component';
const routes: Routes = [
      {
        path:'',
        redirectTo:'base',
        pathMatch:'full'
      },
  
      {
        path:'base',
        component:BaseComponent
      },
      {
        path:'ulogin',
        component:UserloginComponent
      },
      {
        path:'uhome',
        component:UserhomeComponent,
        children:[
          {
            path:'ossupload',
            component:OssuploadComponent
          },
          {
            path:'picturelist',
            component:PicturelistComponent
          },
          {
            path:"oss",
            component:OssComponent
          },
          {
            path:"newslist",
            component:NewslistComponent
          },
          {
            path:"newseditor",
            component:NewseditorComponent
          },
          {
            path:'newInfo/:id',
            component:NewInfoComponent
          },
          {
            path:'content',
            component:EditorComponent
          },
          {
            path:'contentlist',
            component:ContentlistComponent
          },
        ]
      },
      {
        path:'first',
        component:FirstComponent
      },
      {
        path:'login',
        component:LoginComponent
      },
      {
        path:'register',
        component:RegisterComponent
      },
      { 
        path:"home",
        component:HomeComponent,
        canActivate:[AuthGuard],
        children:[
          {
            path:'user',
            loadChildren: ()=> import('./view/user/user.module').then(x=>x.UserModule)
          },
          {
            path:'news',
            component:NewsComponent
          },
        ]
      },
     
    

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
