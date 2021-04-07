import { Component, OnInit } from '@angular/core';
import { AuthorisedSideNavService } from '../services/authorised-side-nav.service';

@Component({
  selector: 'jhi-leftmenu',
  templateUrl: './leftmenu.component.html',
  styleUrls: ['./leftmenu.component.scss']
})
export class LeftmenuComponent implements OnInit {

  constructor(public sideNavService: AuthorisedSideNavService) { }

  ngOnInit() {
  }

}
