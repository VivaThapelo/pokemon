import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { StatsService } from './stats.service';
import { environment } from '../../../../environments/environment';
import { Stats } from '../../models/Stats';

describe('StatsService', () => {
  let service: StatsService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [StatsService]
    });
    service = TestBed.inject(StatsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve the stats for a given ID', () => {
    const mockStats: Stats[] = [
      { id: 1, name: 'Stat 1', base_stat: 10, effort: 0, pokemon_id: 1 },
      { id: 2, name: 'Stat 2', base_stat: 20, effort: 0, pokemon_id: 1 },
      { id: 3, name: 'Stat 3', base_stat: 30, effort: 0, pokemon_id: 1 }
    ];
    const pokemonId = 1;

    service.getPokemonStats(pokemonId).subscribe((stats: Stats[]) => {
      expect(stats).toEqual(mockStats);
    });

    const req = httpMock.expectOne(`${environment.pokemon_stats_endpoint}/${pokemonId}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockStats);
  });

  it('should handle errors when retrieving the stats', () => {
    const pokemonId = 1;

    service.getPokemonStats(pokemonId).subscribe(
        () => {
          fail('Expected error to be thrown');
        },
        (error: any) => {
          expect(error.status).toBe(500);
          expect(error.statusText).toBe('Internal Server Error');
        }
    );

    const req = httpMock.expectOne(`${environment.pokemon_stats_endpoint}/${pokemonId}`);
    expect(req.request.method).toBe('GET');
    req.flush('Internal Server Error', { status: 500, statusText: 'Internal Server Error' });
  });
});
