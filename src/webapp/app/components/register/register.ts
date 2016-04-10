import {Component} from "angular2/core";
import {UserService} from "../../services/user-service";

@Component({
	selector: 'register-component',
	templateUrl: 'app/components/register/register.html',
	styleUrls: ['app/components/register/register.css']
})
export class RegisterComponent {

	constructor(private _userService:UserService) {}

	register(event, email, fullName, password) {
		event.preventDefault();
		this._userService.register(email, fullName, password);
	}

}