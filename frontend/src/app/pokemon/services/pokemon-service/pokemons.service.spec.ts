import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PokemonsService } from './pokemons.service';
import { environment } from '../../../../environments/environment';
import { Pokemon } from '../../models/pokemon';

describe('PokemonsService', () => {
  let service: PokemonsService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PokemonsService]
    });
    service = TestBed.inject(PokemonsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve the list of pokemons', () => {
    const mockPokemons: Pokemon[] = [
      { id: 1, name: 'Pokemon 1' },
      { id: 2, name: 'Pokemon 2' },
      { id: 3, name: 'Pokemon 3' }
    ];

    service.getPokemonList().subscribe((pokemons: Pokemon[]) => {
      expect(pokemons).toEqual(mockPokemons);
    });

    const req = httpMock.expectOne(`${environment.pokemon_list_endpoint}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockPokemons);
  });

  it('should handle errors when retrieving the list of pokemons', () => {
    service.getPokemonList().subscribe(
        () => {
          fail('Expected error to be thrown');
        },
        (error: any) => {
          expect(error.status).toBe(500);
          expect(error.statusText).toBe('Internal Server Error');
        }
    );

    const req = httpMock.expectOne(`${environment.pokemon_list_endpoint}`);
    expect(req.request.method).toBe('GET');
    req.flush('Internal Server Error', { status: 500, statusText: 'Internal Server Error' });
  });
});
