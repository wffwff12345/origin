import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { store } from "../store/store.component";

export class MyInterceptor implements HttpInterceptor{
    state:any;
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const login = req.headers.get('login');
        const upload=req.headers.get("isupload");
        if (!!login) {
            console.log("login")
            return next.handle(req);
        }
        // if(upload){
        //     const uploadreq=req.clone({
        //         headers:req.headers.set("Content-Type","multipart/form-data")
        //     });
        //     return next.handle(uploadreq);
        // }
        this.state = store.getState();
        const token = this.state.token;
        const myreq = req.clone(
            {
                headers: req.headers.set('token', `${token.payload}`)
            }
        );
        return next.handle(myreq);
    }
    
}