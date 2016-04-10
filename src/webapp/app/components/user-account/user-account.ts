import {Component, OnInit} from "angular2/core";
import {Wow} from "../../models/wow";
import {WowService} from "../../services/wow-service";
import {Router, RouteParams, RouterOutlet} from "angular2/router";
import {WowsListComponent} from "../wows-list/wows-list";

@Component({
	templateUrl: 'app/components/user-account/user-account.html',
	directives: [WowsListComponent, RouterOutlet]
})
export class UserAccountComponent implements OnInit {

	public wows:Wow[];

	constructor(private _router:Router,
	            private _routeParams:RouteParams,
	            private _wowService:WowService) {
	}

	ngOnInit() {
		this.fetchWows();
	}

	addWow(text) {
		console.log(text);
		this._wowService.create(text).subscribe(this.fetchWows);
	}

	fetchWows() {
		let name = this._routeParams.get('name');
		this._wowService.findByCreator(name).subscribe(
			wows => this.wows = wows,
			error => console.log(error)
		);
	}

}