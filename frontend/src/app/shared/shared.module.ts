import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ErrorLoggingInterceptor} from "./services/error-logging.interceptor";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: ErrorLoggingInterceptor, multi: true}]
})
export class SharedModule { }
