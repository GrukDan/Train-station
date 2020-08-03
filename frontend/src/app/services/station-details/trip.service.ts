import {Injectable} from '@angular/core';
import {Trip} from "../../models/station-details/trip";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {TripPage} from "../../models/page-models/trip-page";
import {TripRecord} from "../../models/view-models/trip-record";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  private url: string = '/api/trips'

  constructor(private http: HttpClient) {
  }

  save(trip: Trip): Observable<Trip> {
    return this.http.post<Trip>(this.url + '/save', trip);
  }

  getPage(page:number = 0,
          size:number = 5,
          direction:boolean = false,
          parameter:string = "id_task"):Observable<TripPage>{
    return this.http.get<TripPage>(this.url + '/get-page',
      {params:new HttpParams()
          .set('page',page.toString())
          .set('size',size.toString())
          .set('direction',String(direction))
          .set('parameter',parameter)
      })
  }

  getAll():Observable<TripRecord[]>{
    return this.http.get<TripRecord[]>(this.url + '/get-all/trip-record');
  }
}
