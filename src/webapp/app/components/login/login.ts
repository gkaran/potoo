import {Component} from "angular2/core";
import {UserService} from "../../services/user-service";

@Component({
	selector: 'login-component',
	templateUrl: 'app/components/login/login.html',
	styleUrls: ['app/components/login/login.css']
})
export class LoginComponent {

	constructor(private _userService:UserService) {}

	private loginError:string;

	login(event, identifier:string, password:string) {
		event.preventDefault();
		this._userService.login({identifier, password})
			.subscribe(() => this.loginError = null, error => this.loginError = error);
	}

}