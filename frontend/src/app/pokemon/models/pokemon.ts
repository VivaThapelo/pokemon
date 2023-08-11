export class Pokemon {
  id!: Number;
  name!: String;
  url?: String;

  constructor(id: Number, name: String, url: String) {
    this.id = id;
    this.name = name;
    this.url = url;
  }
}
