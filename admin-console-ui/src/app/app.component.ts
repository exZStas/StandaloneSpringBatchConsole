import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'admin-console-ui';
  isExpanded = true;
  state = 'collapsed';

  constructor(public route: ActivatedRoute) { }

  menuItems = [
    {
      title: 'Home',
      icon: 'home',
      url: '/'
    },
    {
      title: 'Jobs',
      icon: 'schedule',
      url: 'jobs'
    },
    {
      title: 'Analytic',
      icon: 'trending_up',
      url: 'analytics'
    }
  ];
}
