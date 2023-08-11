import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse
} from '@angular/common/http';
import {finalize, Observable, tap, throwError} from 'rxjs';

@Injectable()
export class ErrorLoggingInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const started = Date.now();
    let message: string;

    return next.handle(request).pipe(
      tap({
        next: (event) => (message = event instanceof HttpResponse ? 'succeeded' : ''),
        error: (error) => {
          if (error.error instanceof ErrorEvent) {
            console.log('This is client side error');
            message = `Error: ${error.error.message}`;
          } else {
            console.log('This is server side error');
            message = `Error Code: ${error.status},  Message: ${error.message}`;
          }
          return throwError(() => error)
        }
      }),
      finalize(() => {
        const elapsed = Date.now() - started;
        const msg = `${request.method} "${request.urlWithParams}"
             ${message} in ${elapsed} ms.`;
        console.log(msg);
        // TODO: Aws Cloud Watch
        // const params = {
        //   logEvents: [
        //     {
        //       message: msg,
        //       timestamp: elapsed
        //     },
        //   ],
        //   logGroupName: environment.aws_log_group,
        //   logStreamName: environment.aws_log_stream,
        // };
        // this.awsLogs.putLogEvents(params, function(err, data) {
        //   if (err) console.log(err, err.stack);
        //   else     console.log(data);
        // });
      })
    );
  }
}
