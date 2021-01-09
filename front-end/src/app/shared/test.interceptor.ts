import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

export class TestInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      headers: req.headers
        .append('Access-Control-Allow-Origin', '*')
        .append('Access-Control-Allow-Methods','GET,POST,OPTIONS,DELETE,PUT')
        .append('Access-Control-Allow-Credentials', 'true')
    })
    return next.handle(req);
  }

}
