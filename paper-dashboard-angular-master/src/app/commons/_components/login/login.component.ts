import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AuthService} from '../../_services/auth.service';
import {AppStateService} from '../../_services/app-state.service';
import {Router} from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements  OnInit{

  public fromLogin !: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private appStateService: AppStateService,
              private router: Router) {
  }


  ngOnInit(): void {

    this.fromLogin = this.fb.group({
      email : this.fb.control(""),
      password : this.fb.control("")
    })

  }


  handleLogin() {

    let email = this.fromLogin.value.email;
    let password = this.fromLogin.value.password;

    this.authService.login(email, password).subscribe({

      next : data =>  {
        this.appStateService.loadProfile(data);

        /**
         * Les cas d'un employee et d'un admin
         */

        if(this.appStateService.authState.roles.toString() === 'ADMIN'){
          this.router.navigateByUrl('');
        }
      },
      error : err => {
        console.log(err);
      }
    })

  }



}
