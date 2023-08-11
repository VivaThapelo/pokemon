import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, catchError, throwError} from "rxjs";
import {Pokemon} from "../../models/pokemon";
import {Ability} from "../../models/Ability";
import {AbilitiesService} from "../../services/abilities-service/abilities.service";
import {Stats} from "../../models/Stats";
import {StatsService} from "../../services/stats-service/stats.service";
import {HttpResponse, HttpStatusCode} from "@angular/common/http";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit{
  pokemon: Pokemon | undefined;
  id: Number = -1;
  abilities$: BehaviorSubject<Ability[]> = new BehaviorSubject<Ability[]>([]);
  stats$: BehaviorSubject<Stats[]> = new BehaviorSubject<Stats[]>([]);

  constructor(private abilitiesService: AbilitiesService, private statsService: StatsService) {
  }

  ngOnInit() {
    this.pokemon= history.state;
    if(this.pokemon) {
      this.id = this.pokemon && this.pokemon!.id;
      this.abilitiesService.getAbilities(this.id)
        .subscribe(
        abilities => {
            this.abilities$.next(abilities);
        }
      );
      this.statsService.getPokemonStats(this.id)
        .subscribe(
            stats => {
                this.stats$.next(stats);
            });
    }
  }
}
