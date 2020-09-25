import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {ValidationService} from "../../services/reactive-forms/validation.service";
import {FormGroup} from "@angular/forms";
import {JwtRequest} from "../../models/auth/jwt-request";
import {AuthService} from "../../services/auth/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-login-card',
  templateUrl: './login-card.component.html',
  styleUrls: ['./login-card.component.css']
})
export class LoginCardComponent implements OnInit,OnDestroy {

  loginForm:FormGroup;
  jwtRequest:JwtRequest;
  subscriptions:Subscription[] = [];

  constructor(private validationService:ValidationService,
              private authService:AuthService) { }

  ngOnInit() {
    this.loginForm = this.validationService.getLoginFormGroup();
    this.jwtRequest = new JwtRequest();
  }

  submit(jwtRequest:JwtRequest) {
    this.login(jwtRequest);
    this.loginForm.reset();
  }

  login(jwtRequest:JwtRequest){
    this.subscriptions.push(
      this.authService.logIn(jwtRequest)
        .subscribe(token=>{
          console.log(token);
    }))
  }

  get _email(){
    return this.loginForm.get('email');
  }

  get _password(){
    return this.loginForm.get('password');
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription=> subscription.unsubscribe())
  }
}
