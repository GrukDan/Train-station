import { Component, OnInit } from '@angular/core';
import {CarouselConfig} from "ngx-bootstrap/carousel";
import {AuthService} from "../../services/auth/auth.service";
import {JwtRequest} from "../../models/auth/jwt-request";

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css'],
  providers: [
    { provide: CarouselConfig, useValue: { interval: 1500, noPause: false, showIndicators: true } }
  ]
})
export class WelcomePageComponent implements OnInit {

  constructor(public authService:AuthService) { }

  ngOnInit() {
  }
}
