import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AbilitiesService } from './abilities.service';
import { Ability } from '../../models/Ability';
import { environment } from '../../../../environments/environment';

describe('AbilitiesService', () => {
  let service: AbilitiesService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AbilitiesService]
    });
    service = TestBed.inject(AbilitiesService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve abilities for a given ID', () => {
    const mockAbilities: Ability[] = [
      { id: 1, name: 'Ability 1', pokemonId: 1 },
      { id: 2, name: 'Ability 2', pokemonId: 1 },
      { id: 3, name: 'Ability 3', pokemonId: 1 }
    ];
    const pokemonId = 1;

    service.getAbilities(pokemonId).subscribe((abilities: Ability[]) => {
      expect(abilities).toEqual(mockAbilities);
    });

    const req = httpMock.expectOne(`${environment.pokemon_abilities_endpoint}/${pokemonId}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockAbilities);
  });
});
