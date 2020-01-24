import {Component, OnInit} from '@angular/core';
import {environment} from '../../../environments/environment';
import {ApiService} from '../../services/api/api.service';
import {NavController} from '@ionic/angular';

@Component({
  selector: 'app-settings',
  templateUrl: './settings-page.component.html',
  styleUrls: ['./settings-page.component.scss']
})
export class SettingsPage implements OnInit {
  apiUrl = '';

  constructor(
    private apiService: ApiService,
    private navController: NavController) {
  }

  ngOnInit() {
    this.apiUrl = String(ApiService.API_URL);
  }

  saveSettings() {
    this.apiService.setApiUrl(this.apiUrl);
    this.navController.navigateRoot('');
  }

  resetUrl() {
    this.apiUrl = String(environment.apiUrl);
  }
}
