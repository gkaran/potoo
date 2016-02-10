import {Component} from 'angular2/core';
import {Wow}       from '../../models/wow';

@Component({
    selector: 'wow-summary',
    templateUrl: 'app/components/wow-summary/template.html',
    styleUrls: ['app/components/wow-summary/style.css'],
    inputs: ['wow']
})
export class WowSummaryComponent {

    public wow: Wow;

}