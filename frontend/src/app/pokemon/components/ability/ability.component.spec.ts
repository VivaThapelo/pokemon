import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AbilityComponent } from './ability.component';
import {AbilitiesService} from "../../services/abilities-service/abilities.service";

describe('AbilityComponent', () => {
  let component: AbilityComponent;
  let fixture: ComponentFixture<AbilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AbilityComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AbilityComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the ability name', () => {
    const abilityName = 'Electric Surge';
    component.name = abilityName;

    fixture.detectChanges();

    const abilityNameElement: HTMLElement = fixture.nativeElement.querySelector('h5');
    expect(abilityNameElement.textContent).toBe(abilityName);
  });
});
