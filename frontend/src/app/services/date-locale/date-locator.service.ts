import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class DateLocatorService {

  maxDate: Date = new Date();
  minDate: Date = new Date(1850, 1, 1);
  dateConfig = {
    isAnimated: true,
    dateInputFormat: 'DD.MM.YYYY'
  };

  constructor() {
  }

}
