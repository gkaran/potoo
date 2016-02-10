import {Component, OnInit}   from 'angular2/core';
import {HTTP_PROVIDERS}      from 'angular2/http';
import {WowSummaryComponent} from '../wow-summary/component';
import {WowService}          from '../../services/wow-service';
import {Wow}                 from '../../models/wow';

@Component({
	selector: 'my-app',
	templateUrl: 'app/components/app/template.html',
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