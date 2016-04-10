import {Injectable} from "angular2/core";
import {Http, Headers} from "angular2/http";
import {contentHeaders} from "../common/headers";
import {Observable} from "rxjs/Rx";

@Injectable()
export class UserService {

	private loggedIn = false;

	constructor(private http:Http) {}

	checkIfLoggedIn() {
		let authHeaders = new Headers();
		authHeaders.append('Authorization', `Bearer ${localStorage.getItem('jwt')}`);

		return new Observable.create(observer => {
			this.http.get('/api/accounts/current', {headers: authHeaders})
				.subscribe(response => observer.next(response.text()), observer.error);
		});
	}

	login(credentials) {
		let body = JSON.stringify({username: credentials.identifier, password: credentials.password});

		return new Observable.create(observer => {
			this.http.post('/api/accounts/login', body, {headers: contentHeaders})
				.subscribe(response => {
					localStorage.setItem('jwt', response.text());
					this.loggedIn = true;
					observer.complete();
				}, error => {
					this.loggedIn = false;
					alert(error.text());
					observer.error(error.text());
				});
		});
	}

	register(email, fullName, password) {
		let body = JSON.stringify({email, fullName, password});
		this.http.post('/api/accounts/register', body, {headers: contentHeaders})
			.subscribe(
				response => this.login({email, password}),
				error => {
					alert(error.text());
					console.log(error.text());
				}
			);
	}

}