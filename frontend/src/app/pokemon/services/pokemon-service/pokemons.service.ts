import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pokemon} from "../../models/pokemon";
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PokemonsService {

  constructor(private http: HttpClient) { }

  getPokemonList() : Observable<Pokemon[]> {
    return this.http.get<Pokemon[]>(`${environment.pokemon_list_endpoint}`);
  }
}
