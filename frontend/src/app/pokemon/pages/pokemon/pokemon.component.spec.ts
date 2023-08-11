import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BehaviorSubject, of } from 'rxjs';
import { PokemonComponent } from './pokemon.component';
import { PokemonsService } from '../../services/pokemon-service/pokemons.service';
import { Pokemon } from '../../models/pokemon';
import {ItemComponent} from "../../components/item/item.component";
import {ImageService} from "../../services/image-service/image.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";

describe('PokemonComponent', () => {
  let component: PokemonComponent;
  let fixture: ComponentFixture<PokemonComponent>;
  let mockPokemonsService: jasmine.SpyObj<PokemonsService>;

  beforeEach(async () => {
    mockPokemonsService = jasmine.createSpyObj('PokemonsService', ['getPokemonList']);
    await TestBed.configureTestingModule({
      declarations: [PokemonComponent, ItemComponent],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [ImageService,{ provide: PokemonsService, useValue: mockPokemonsService }]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
