import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StatComponent } from './stat.component';
import {AbilitiesService} from "../../services/abilities-service/abilities.service";

describe('StatComponent', () => {
  let component: StatComponent;
  let fixture: ComponentFixture<StatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StatComponent],
      providers: [AbilitiesService]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StatComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the id, pokemon_id, stat name, base stat, and effort', () => {
    const stat = {
      name: 'Attack',
      base_stat: 100,
      effort: 2,
      id: 1,
      pokemon_id: 1
    };
    component.stat = stat;

    fixture.detectChanges();


    const statNameElement: HTMLElement = fixture.nativeElement.querySelector('.stat-row:first-child');
    const baseStatElement: HTMLElement = fixture.nativeElement.querySelector('.stat-row:nth-child(2)');
    const effortElement: HTMLElement = fixture.nativeElement.querySelector('.stat-row:nth-child(3)');

    expect(statNameElement.textContent).toContain(stat.name);
    expect(baseStatElement.textContent).toContain(stat.base_stat);
    expect(effortElement.textContent).toContain(stat.effort);
  });
});
