import {Component, OnInit}    from 'angular2/core';
import {HTTP_PROVIDERS}       from 'angular2/http';
import {RouteConfig, RouterOutlet} from 'angular2/router';
import {UserAccountComponent} from './user-account';
import {Wow}                  from '../models/wow';

@Component({
    selector: 'my-app',
    template: `
        <h1>Welcome to Potoo (you had it coming bro)</h1>
        <router-outlet></router-outlet>
    `,
    directives: [RouterOutlet]
})
@RouteConfig([
    {path: '/:name', name: 'Account', component: UserAccountComponent}
])
export class AppComponent {}