export class Stats {
  id: Number;
  pokemon_id: Number;
  name: string;
  base_stat: Number;
  effort: Number;

  constructor(id: Number, pokemonId: Number, name: string, base_stat: Number, effort:Number) {
    this.id = id;
    this.pokemon_id = pokemonId;
    this.name = name;
    this.base_stat = base_stat;
    this.effort = effort;
  }
}
