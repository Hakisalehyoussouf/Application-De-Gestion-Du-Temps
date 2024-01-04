import {AfterViewInit, Component, Input, OnInit} from '@angular/core';


// @ts-ignore
@Component({
  selector: 'app-widget',
  templateUrl: './widget.component.html',
  styleUrls: ['./widget.component.css'],
})
export class WidgetComponent implements OnInit{



  @Input() type: string = '';

  //Il faut le remplacer par un service et comparer avec celles du moi precedent
  @Input() diff: number = -3;
  data: any;




  constructor() {}

  ngOnInit() {
    switch (this.type) {
      case 'heuresTravail':
        this.data = {
          title: 'Heures Travail',
          isMoney: false,
        };
        break;
      case 'heuresPause':
        this.data = {
          title: 'Heures Pause',
          isMoney: false,
        };
        break;
      case 'heureSupplementaires':
        this.data = {
          title: 'Heures Supplementaires',
          isMoney: false,
        };
        break;
      case 'joursAbsent':
        this.data = {
          title: 'Jours Absents',
          isMoney: false,
        };
        break;
      default:
        break;
    }
  }



}
