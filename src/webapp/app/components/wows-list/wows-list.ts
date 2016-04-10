import {Component, EventEmitter} from 'angular2/core';
import {Wow}       from '../../models/wow';
// import {WowService} from "../../services/wow-service";

@Component({
	selector: 'wows-list',
	templateUrl: 'app/components/wows-list/wows-list.html',
	styleUrls: ['app/components/wows-list/wows-list.css'],
	inputs: ['wows', 'includeCreateWow'],
	outputs: ['newWow']
})
export class WowsListComponent {

	public wows:Wow[];
	public includeCreateWow:boolean = true;
	public newWow = new EventEmitter<String>();

	// constructor(private _wowService:WowService) {}

	createWow(event, text:string) {
		event.preventDefault();

		console.log(text);
		this.newWow.emit(text);

		// this._wowService.create(text).subscribe();
	}
	
}