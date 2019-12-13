import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.page.html',
  styleUrls: ['./publisher.page.scss'],
})
export class PublisherPage implements OnInit {
	publishers: Array<any> = [
		{name: 'publisher01', id: 1},
		{name: 'publisher02', id: 2},
  	];

  constructor() { }

  ngOnInit() {
  }

}
