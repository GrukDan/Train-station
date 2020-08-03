import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ValidationService} from "../../services/reactive-forms/validation.service";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-login-card',
  templateUrl: './login-card.component.html',
  styleUrls: ['./login-card.component.css']
})
export class LoginCardComponent implements OnInit {

  loginForm:FormGroup;

  constructor(private validationService:ValidationService) { }

  ngOnInit() {
    this.loginForm = this.validationService.getLoginFormGroup();
  }

  @Input() email:string;
  @Input() password:string;

  @Output() emailChange = new EventEmitter<string>();
  @Output() passwordChange = new EventEmitter<string>();
  @Output() clickButton = new EventEmitter<boolean>();

  onEmailChange(email: string){
    this.email = email;
    this.emailChange.emit(email);
  }

  onPasswordChange(password: string){
    this.password = password;
    this.emailChange.emit(password);
  }

  change(increased:any) {
    this.clickButton.emit(increased);
  }

  get _email(){
    return this.loginForm.get('email');
  }

  get _password(){
    return this.loginForm.get('password');
  }
}
