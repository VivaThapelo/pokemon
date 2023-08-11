import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ability} from "../../models/Ability";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AbilitiesService {

  constructor(private http: HttpClient) { }

  getAbilities(id: Number) : Observable<Ability[]> {
    return this.http.get<Ability[]>(`${environment.pokemon_abilities_endpoint}/${id}`);
  }
}
