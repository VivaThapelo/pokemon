export class Ability {
  id: Number;
  pokemonId: Number;
  name: String;

  constructor(id: Number, pokemonId: Number, name: String) {
    this.id = id;
    this.pokemonId =pokemonId;
    this.name = name;
  }
}
