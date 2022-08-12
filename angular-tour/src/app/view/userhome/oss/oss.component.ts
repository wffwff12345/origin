import { HttpClient, HttpRequest, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { filter } from 'rxjs/operators';
import { OssService } from './oss.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzUploadFile } from 'ng-zorro-antd/upload';
@Component({
  selector: 'app-oss',
  templateUrl: './oss.component.html',
  styleUrls: ['./oss.component.css']
})
export class OssComponent implements OnInit {

  uploading = false;
  fileList: NzUploadFile[] = [];

  constructor(private service: OssService, private message: NzMessageService) {}
  ngOnInit(): void {
  }

  beforeUpload = (file: NzUploadFile): boolean => {
    this.fileList = this.fileList.concat(file);
    return false;
  };

  handleUpload(): void {
    this.fileList.forEach((file: any) => {
      const formData = new FormData();
      formData.append('file', file, file.name);
      this.service.upload(formData).subscribe((res: any) => {
        console.log(res);
        if (res.code == 1001) {
          this.message.success("成功上传图片");
        } else {
          this.message.error(res.message);
        }
      })
    });


  }
}