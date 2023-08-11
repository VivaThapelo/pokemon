import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PokemonComponent} from "./pages/pokemon/pokemon.component";
import {DetailsComponent} from "./pages/details/details.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: "full",
    component: PokemonComponent,
  },
  {
    path: 'details',
    component: DetailsComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PokemonRoutingModule { }
