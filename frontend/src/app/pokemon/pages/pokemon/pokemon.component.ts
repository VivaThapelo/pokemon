import {Component, OnInit} from '@angular/core';
import {PokemonsService} from "../../services/pokemon-service/pokemons.service";
import {Pokemon} from "../../models/pokemon";
import {BehaviorSubject, throwError} from "rxjs";
import {HttpStatusCode} from "@angular/common/http";
@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.scss']
})
export class PokemonComponent implements OnInit{
  pokemons$: BehaviorSubject<Pokemon[]> = new BehaviorSubject<Pokemon[]>([]);

  constructor(private service: PokemonsService) {
  }

  ngOnInit(): void {
    this.service.getPokemonList().subscribe( pokemons=> {
        this.pokemons$.next(pokemons);
    });
  }

}
