import {Component}    from 'angular2/core';
import {RouteConfig, RouterOutlet} from 'angular2/router';
import {HTTP_PROVIDERS}      from 'angular2/http';
import {UserAccountComponent} from './../user-account/user-account';
import {NavigationBarComponent} from './../navigation-bar/navigation-bar';
import {IntroComponent} from './../intro/intro';
import {WowService} from "../../services/wow-service";
import {UserService} from "../../services/user-service";

@Component({
	selector: 'my-app',
	templateUrl: 'app/components/application/application.html',
	directives: [RouterOutlet, NavigationBarComponent],
	providers: [HTTP_PROVIDERS, UserService, WowService]
})
@RouteConfig([
	{path: '/:name', name: 'Account', component: UserAccountComponent},
	{path: '/', name: 'Intro', component: IntroComponent}
])
export class AppComponent {
}