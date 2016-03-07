import {Component} from '../../node_modules/angular2/core.d';
import {Wow}       from '../models/wow';

@Component({
    selector: 'wow-summary',
    template: `
        <div class="wow-summary">
            <p class="creator-name">{{wow.creator}}</p>
            <p class="wow-message">{{wow.text}}</p>
            <p class="wow-time-created">{{wow.createdDate}}</p>
        </div>
    `,
    styles: [`
        .wow-summary {
            width: 500px;
            background-color: lightblue;
            border: 1px solid darkblue;
            padding: 5px;
            margin-bottom: 10px;
        }
        
        .wow-summary p {
            margin: 0;
        }
        
        .creator-name {
            font-weight: bold;
        }
    `],
    inputs: ['wow']
})
export class WowSummaryComponent {

    public wow: Wow;

}