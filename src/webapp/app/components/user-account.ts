import {Component, OnInit}   from 'angular2/core';
import {HTTP_PROVIDERS}      from 'angular2/http';
import {Wow}                 from '../models/wow';
import {WowSummaryComponent} from './wow-summary';
import {WowService}          from "../services/wow-service";
import {Router, RouteParams, RouteConfig, RouterOutlet} from "angular2/router";

@Component({
    template: `
        <router-outlet></router-outlet>
        <wow-summary *ngFor="#wow of wows; #i=index" [wow]="wow" [index]="wow"></wow-summary>
    `,
    styles: [``],
    directives: [WowSummaryComponent, RouterOutlet],
    providers: [HTTP_PROVIDERS, WowService]
})
export class UserAccountComponent implements OnInit {

    public wows:Wow[];

    constructor(private _router:Router,
                private _routeParams:RouteParams,
                private _wowService:WowService) {
    }

    ngOnInit() {
        let name = this._routeParams.get('name');
        this._wowService.findByCreator(name)
            .subscribe(
                wows => this.wows = wows,
                error => console.log(error)
            );
    }

}