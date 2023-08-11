import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BehaviorSubject, of } from 'rxjs';
import { DetailsComponent } from './details.component';
import { AbilitiesService } from '../../services/abilities-service/abilities.service';
import { StatsService } from '../../services/stats-service/stats.service';
import { Ability } from '../../models/Ability';
import { Stats } from '../../models/Stats';

describe('DetailsComponent', () => {
  let component: DetailsComponent;
  let fixture: ComponentFixture<DetailsComponent>;
  let mockAbilitiesService: jasmine.SpyObj<AbilitiesService>;
  let mockStatsService: jasmine.SpyObj<StatsService>;

  beforeEach(async () => {
    mockAbilitiesService = jasmine.createSpyObj('AbilitiesService', ['getAbilities']);
    mockStatsService = jasmine.createSpyObj('StatsService', ['getPokemonStats']);
    await TestBed.configureTestingModule({
      declarations: [DetailsComponent],
      providers: [
        { provide: AbilitiesService, useValue: mockAbilitiesService },
        { provide: StatsService, useValue: mockStatsService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
