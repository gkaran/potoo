import {Component} from "angular2/core";
import {LoginComponent} from "./../login/login";
import {RegisterComponent} from "./../register/register";
import {UserService} from "../../services/user-service";
import {Wow} from "../../models/wow";
import {Router} from "angular2/router";

@Component({
	selector: 'intro',
	templateUrl: 'app/components/intro/intro.html',
	styleUrls: ['app/components/intro/intro.css'],
	directives: [LoginComponent, RegisterComponent]
})
export class IntroComponent {

	private loggedIn:boolean= false;
	private wows:Wow[];

	constructor(private _userService:UserService, private _router:Router) {
		_userService.checkIfLoggedIn().subscribe(userName => {
			this.loggedIn = true;
			//TODO: Get all wows from followers here - for now just redirect to user account page
			this._router.navigate(['Account', {name: userName}]);
		}, () => {
			this.loggedIn = false;
			this.wows = [];
		});
	}

}