import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  visible: boolean = false;

  showDialog() {
    this.visible = true;
  }
}
