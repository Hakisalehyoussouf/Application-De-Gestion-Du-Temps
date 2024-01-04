import { Component, OnInit } from '@angular/core';
import { HumeurService } from '../../services/humeur.service';

@Component({
  selector: 'humeur-cmp',
  moduleId: module.id,
  templateUrl: 'humeur.component.html'
})
export class HumeurComponent implements OnInit {
  humeurs: any[] = [];

  constructor(private humeurService: HumeurService) {}

  ngOnInit() {
    this.loadHumeurs();
  }

  loadHumeurs() {
    this.humeurService.getAllHumeurs().subscribe(data => {
      this.humeurs = data;
    });
  }
}
