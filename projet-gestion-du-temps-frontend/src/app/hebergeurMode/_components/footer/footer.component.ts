import { Component} from '@angular/core';
import {
  faFacebook,
  faGithub,
  faYoutube,
  faTwitter,
} from '@fortawesome/free-brands-svg-icons';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  public faGithub = faGithub;
  public faFacebook = faFacebook;
  public faYoutube = faYoutube;
  public faTwitter = faTwitter;

}
