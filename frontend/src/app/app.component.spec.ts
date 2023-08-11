import { TestBed, ComponentFixture } from '@angular/core/testing';
import { AppComponent } from './app.component';
import {NavBarComponent} from "./core/component/navbar/nav-bar.component";
import {RouterTestingModule} from "@angular/router/testing";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ErrorLoggingInterceptor} from "./shared/services/error-logging.interceptor";

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppComponent, NavBarComponent],
      imports: [RouterTestingModule],
      providers: []
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

});
