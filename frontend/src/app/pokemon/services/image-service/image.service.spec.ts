import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ImageService } from './image.service';
import { environment } from '../../../../environments/environment';

describe('ImageService', () => {
  let service: ImageService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ImageService]
    });
    service = TestBed.inject(ImageService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve the image URL for a given ID', () => {
    const mockImageUrl = 'https://example.com/image.png';
    const pokemonId = 1;

    service.getImage(pokemonId).subscribe(imageUrl=> {
      expect(imageUrl).toEqual(mockImageUrl);
    });

    const req = httpMock.expectOne(`${environment.pokemon_image_endpoint}/${pokemonId}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockImageUrl);
  });

  it('should handle errors when retrieving the image URL', () => {
    const pokemonId = 1;

    service.getImage(pokemonId).subscribe(
        () => {
          fail('Expected error to be thrown');
        },
        (error: any) => {
          expect(error.status).toBe(500);
          expect(error.statusText).toBe('Internal Server Error');
        }
    );

    const req = httpMock.expectOne(`${environment.pokemon_image_endpoint}/${pokemonId}`);
    expect(req.request.method).toBe('GET');
    req.flush('Internal Server Error', { status: 500, statusText: 'Internal Server Error' });
  });
});
