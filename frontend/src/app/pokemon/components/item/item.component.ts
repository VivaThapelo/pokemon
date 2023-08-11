import {Component, Input, OnInit} from '@angular/core';
import {Pokemon} from '../../models/pokemon';
import {ImageService} from "../../services/image-service/image.service";
import {BehaviorSubject, throwError} from "rxjs";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit{
  imageUrl$: BehaviorSubject<String> = new BehaviorSubject<String>('');
  constructor(private service: ImageService) {
  }

  @Input() pokemon: Pokemon | undefined;
  @Input() size: Number = 200;
  private placeHolder = environment.pokemon_image_endpoint;

  getImageUrl(id: Number): void {
    this.service.getImage(id)
      .subscribe(
        imageUrl => {
            this.imageUrl$.next(imageUrl);
        } ,
      );
  }

  ngOnInit(): void {
    if (this.pokemon?.id) {
      this.getImageUrl(this.pokemon.id);
      console.log(this.imageUrl$.value);
    }
  }
}
