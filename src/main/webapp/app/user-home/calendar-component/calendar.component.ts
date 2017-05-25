/**
 * Created by Kolja on 24.05.2017.
 */
import { Component, ChangeDetectionStrategy, ViewEncapsulation, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CalendarEvent, CalendarMonthViewDay } from 'angular-calendar';
import {UserHomeService} from "../user-home.service";

@Component({
    selector: 'mwl-demo-component',
    changeDetection: ChangeDetectionStrategy.OnPush,
    templateUrl: 'calendar.component.html',
    styleUrls: ['calendar.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CalendarComponent {

    date: Date;
    viewDate: Date = new Date();
    selectedDay: CalendarMonthViewDay;


    @Input() excludeDays: number[] = [];
    @Input() weekStartsOn: number;

    selectDay: (day: CalendarMonthViewDay) => void;

    constructor(private userHomeService:UserHomeService) {
        this.selectDay = (day: CalendarMonthViewDay): void => {
            if (this.selectedDay && day.date.getTime() === this.selectedDay.date.getTime()) {
                day.cssClass = 'cal-day-selected';
            }
        };

    }

    onSubmit(): void {
        // console.log('Sibling1Component-received from sibling2: ' + this._sharedService.subscribeData());
        console.log('Form submitted-sibling1Form');
        let dateToSend = this.selectedDay.date;
        this.userHomeService.publishData(dateToSend);
    }

    dayClicked(day: CalendarMonthViewDay): void {
        this.selectedDay = day;
        this.date = this.selectedDay.date;
        console.log(this.selectedDay.date);
        console.log(this.date);
        this.onSubmit();
    }



}