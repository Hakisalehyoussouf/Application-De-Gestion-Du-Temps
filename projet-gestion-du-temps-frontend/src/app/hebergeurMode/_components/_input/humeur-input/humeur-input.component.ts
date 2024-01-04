import { Component } from '@angular/core';
import {HumeurInputService} from "../../../../_services/_input/humeur-input.service";
import {take} from "rxjs";

@Component({
  selector: 'app-humeur-input',
  templateUrl: './humeur-input.component.html',
  styleUrls: ['./humeur-input.component.css']
})
export class HumeurInputComponent {


  public  humeur: string= ""; // Je doit faire la meme chose avec les ville?????????????

  constructor(public his : HumeurInputService) {
  }

  changeHumeurInput(event: any) {
    if(event.target.value!==""){
      this.humeur = event.target.value;
      this.his.setHumeur(event.target.value)
      let temp : string = '';
      this.his.humeur$.pipe(take(1)).subscribe({
        next : value => {
          temp = value;
          console.log(temp);
        }
      })
    }
  }

}
