import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {PokemonComponent} from './pages/pokemon/pokemon.component';
import {DetailsComponent} from './pages/details/details.component';
import {PokemonRoutingModule} from "./pokemon-routing.module";
import {ImageService} from "./services/image-service/image.service";
import {AbilityComponent} from './components/ability/ability.component';
import {StatComponent} from './components/stat/stat.component';
import {ItemComponent} from "./components/item/item.component";
import {AbilitiesService} from "./services/abilities-service/abilities.service";
import {StatsService} from "./services/stats-service/stats.service";
import {PokemonsService} from "./services/pokemon-service/pokemons.service";

@NgModule({
  declarations: [
    PokemonComponent,
    DetailsComponent,
    ItemComponent,
    AbilityComponent,
    StatComponent
  ],
  imports: [
    CommonModule,
    PokemonRoutingModule,
    NgOptimizedImage
  ],
  providers: [ImageService, AbilitiesService, StatsService, PokemonsService]
})
export class PokemonModule { }
