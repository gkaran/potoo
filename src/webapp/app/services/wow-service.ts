import {Injectable}     from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';
import {Wow}            from '../models/wow';
import {contentHeaders} from "../common/headers";
import 'rxjs/Rx';

@Injectable()
export class WowService {

	constructor(private http:Http) {}

	private _wowsUrl = '/api/wows';

	findAll() {
		return this.http.get(this._wowsUrl)
			.map(res => <Wow[]> res.json()._embedded.wows)
			.catch(this.handleError);
	}

	findByCreator(creator:string) {
		return this.http.get(`${this._wowsUrl}/search/creator?creator=${creator}`)
			.map(res => <Wow[]> res.json()._embedded.wows)
			.catch(this.handleError);
	}
	
	create(text:string) {
		let headers = contentHeaders;
		headers.append('Authorization', `Bearer ${localStorage.getItem('jwt')}`);
		let body = JSON.stringify({text});

		return this.http.post(this._wowsUrl, body, {headers});
	}

	private handleError(error:Response) {
		console.error(error);
		return Observable.throw(error.json().error || 'Server error');
	}

}