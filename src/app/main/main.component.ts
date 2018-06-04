import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(

    private securityService: AuthService,
    private router: Router

  ) { }

  ngOnInit() {
  }

  onLogout(){
    this.securityService.logout();
    this.router.navigate(['auth/login'])
  }

}
