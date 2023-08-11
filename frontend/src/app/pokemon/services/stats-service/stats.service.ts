import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {Observable} from "rxjs";
import {Stats} from "../../models/Stats";
import {HttpClient, HttpResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  constructor(private http: HttpClient) { }

  getPokemonStats(id: Number) : Observable<Stats[]> {
    return this.http.get<Stats[]>(`${environment.pokemon_stats_endpoint}/${id}`);
  }
}
