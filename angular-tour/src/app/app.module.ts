import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatSliderModule } from '@angular/material/slider';
import { NzFormModule} from 'ng-zorro-antd/form';
import { ReactiveFormsModule } from '@angular/forms';
import { NzButtonModule} from 'ng-zorro-antd/button';
import { NzInputModule} from 'ng-zorro-antd/input'
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzSelectModule } from 'ng-zorro-antd/select'
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzPopconfirmModule } from 'ng-zorro-antd/popconfirm';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzImageModule } from 'ng-zorro-antd/image';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';



import { AppComponent } from './app.component';
import { OssuploadComponent } from './view/userhome/ossupload/ossupload.component';
import { FirstComponent } from './view/first/first.component';
import { Child2Component } from './view/user/child2/child2.component';
import { LoginComponent } from './view/login/login.component';
import { UserService } from './service/user.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './view/home/home.component';
import { RegisterComponent } from './view/register/register.component';
import { UserhomeComponent } from './view/userhome/userhome/userhome.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import { MyInterceptor } from './httpinterceptor/myInterceptor';
import zh from '@angular/common/locales/zh';
import { ErrorComponent } from './error/error.component';
import { Child1Component } from './view/user/child1/child1.component';
import { PicturelistComponent } from './view/userhome/picturelist/picturelist.component';
import { UserloginComponent } from './view/userlogin/userlogin.component';
//import { PicturelistComponent } from './view/userhome/picturelist/picturelist.component';
import { BaseComponent } from './view/base/base.component';
import { OssComponent } from './view/userhome/oss/oss.component';
import { NewslistComponent } from './view/userhome/newslist/newslist.component';
import { NewseditorComponent } from './view/userhome/newseditor/newseditor.component';
import { NewInfoComponent } from './view/userhome/new-info/new-info.component';
import { EditorComponent } from './view/userhome/editor/editor.component';
import { ContentlistComponent } from './view/userhome/new-info/contentlist/contentlist.component';
import { ShowpicComponent } from './view/userhome/newslist/showpic/showpic.component';
import { NewsComponent } from './view/news/news.component';
registerLocaleData(zh);
@NgModule({
  declarations: [
    AppComponent,
    FirstComponent,
    Child1Component,
    Child2Component,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    ErrorComponent,
    OssComponent,
    BaseComponent,
    UserloginComponent,
    UserhomeComponent,
    OssuploadComponent,
    PicturelistComponent,
    NewslistComponent,
    NewseditorComponent,
    NewInfoComponent,
    EditorComponent,
    ContentlistComponent,
    ShowpicComponent,
    NewsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    NzButtonModule,
    NzFormModule,
    ReactiveFormsModule,
    NzInputModule,
    NzMenuModule,
    NzToolTipModule,
    NzIconModule,
    NzLayoutModule,
    NzSelectModule,
    NzTableModule,
    NzDividerModule,
    NzPopconfirmModule,
    NzMessageModule,
    NzPaginationModule,
    NzRadioModule,
    NzModalModule,
    NzUploadModule,
    NzCardModule,
    NzImageModule,
    NzDatePickerModule
  ],
  providers: [UserService, NzMessageService,{ provide: NZ_I18N, useValue: zh_CN },
    {
      provide:HTTP_INTERCEPTORS,
      useClass:MyInterceptor,
      multi:true,
    }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
