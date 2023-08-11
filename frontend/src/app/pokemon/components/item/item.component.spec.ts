import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BehaviorSubject, of } from 'rxjs';
import { ItemComponent } from './item.component';
import { ImageService } from '../../services/image-service/image.service';
import { Pokemon } from '../../models/pokemon';
import {NgOptimizedImage} from "@angular/common";

describe('ItemComponent', () => {
  let component: ItemComponent;
  let fixture: ComponentFixture<ItemComponent>;
  let mockImageService: jasmine.SpyObj<ImageService>;

  beforeEach(async () => {
    mockImageService = jasmine.createSpyObj('ImageService', ['getImage']);
    await TestBed.configureTestingModule({
      declarations: [ItemComponent],
      imports: [NgOptimizedImage],
      providers: [{ provide: ImageService, useValue: mockImageService }]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should retrieve the image URL on initialization', () => {
    const mockImageUrl = 'https://example.com/image.png';
    const mockPokemon: Pokemon = { id: 1, name: 'Pokemon 1' };

    mockImageService.getImage.and.returnValue(of(mockImageUrl));

    component.pokemon = mockPokemon;
    fixture.detectChanges();

    expect(component.imageUrl$.value).toBe(mockImageUrl);
  });
});
