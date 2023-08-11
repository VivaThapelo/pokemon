import {Component, Input} from '@angular/core';
import {Stats} from "../../models/Stats";

@Component({
  selector: 'app-stat',
  templateUrl: './stat.component.html',
  styleUrls: ['./stat.component.scss']
})
export class StatComponent {

  @Input() stat: Stats | undefined;

}
