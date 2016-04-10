import {Component} from "angular2/core";
import {RouterLink} from 'angular2/router';

@Component({
	selector: 'navigation-bar',
	templateUrl: 'app/components/navigation-bar/navigation-bar.html',
	styleUrls: ['app/components/navigation-bar/navigation-bar.css'],
	directives: [RouterLink]
})
export class NavigationBarComponent {
}