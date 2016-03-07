import {Component, OnInit}   from '../../node_modules/angular2/core.d';
import {HTTP_PROVIDERS}      from '../../node_modules/angular2/http.d';
import {WowSummaryComponent} from './wow-summary';
import {WowService}          from '../services/wow-service';
import {Wow}                 from '../models/wow';

@Component({
	selector: 'my-app',
	template: `
		<h1>Welcome to Potoo (you had it coming bro)</h1>
		<wow-summary *ngFor="#wow of wows; #i=index" [wow]="wow" [index]="wow"></wow-summary>
	`,
	directives: [WowSummaryComponent],
	providers: [HTTP_PROVIDERS, WowService]
})
export class AppComponent implements OnInit {

	public wows: Wow[];

	constructor(private _wowService:WowService) { }

	ngOnInit() {

		this._wowService.getWows()
			.subscribe(
				wows => this.wows = wows,
				error => console.log(error)
			);
	}


}