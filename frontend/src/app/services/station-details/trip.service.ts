import {Injectable} from '@angular/core';
import {Trip} from "../../models/station-details/trip";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TripPage} from "../../models/page-models/trip-page";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  private url: string = '/api/trips'

  constructor(private http: HttpClient) {
  }

  save(trip: Trip): Observable<Trip> {
    console.log(trip);
    return null;
    //return this.http.post<Trip>(this.url + '/save', trip);
  }

  getPage(tripPage:TripPage):Observable<TripPage>{
    return this.http.get<TripPage>(this.url + '/')
  }
}
