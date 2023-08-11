import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {CoreRoutingModule} from "./core-routing.module";
import {NavBarComponent} from './component/navbar/nav-bar.component';


@NgModule({
  declarations: [PageNotFoundComponent, NavBarComponent],
  exports: [
    NavBarComponent,
    PageNotFoundComponent,
  ],
  imports: [
    CommonModule,
    CoreRoutingModule
  ],
})
export class CoreModule { }
