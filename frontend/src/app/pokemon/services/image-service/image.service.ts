import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) { }

  getImage(id: Number): Observable<String> {
      return this.http.get<String>(`${environment.pokemon_image_endpoint}/${id}`);
  }
}
