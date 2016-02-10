import {Injectable}     from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';
import {Wow}            from '../models/wow';
import 'rxjs/Rx';

@Injectable()
export class WowService {

	constructor(private http:Http) {}

	private _wowsUrl = '/api/wows';

	getWows() {
		return this.http.get(this._wowsUrl)
			.map(res => <Wow[]> res.json())
			.catch(this.handleError);
	}

	private handleError(error:Response) {
		// in a real world app, we may send the server to some remote logging infrastructure
		// instead of just logging it to the console
		console.error(error);
		return Observable.throw(error.json().error || 'Server error');
	}

}