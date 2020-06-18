import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {TrainModel} from "../../models/station-details/train-model";
import {Observable} from "rxjs";
import {Station} from "../../models/station-details/station";

@Injectable({
  providedIn: 'root'
})
export class StationService {

  private url: string = "/api/stations";

  constructor(private http: HttpClient) {
  }

  public save(station: Station): Observable<Station> {
    return this.http.post<Station>(this.url + '/save', station);
  }

  public getAll(): Observable<Station[]> {
    return this.http.get<Station[]>(this.url + '/get-all');
  }

  public getAllByCity(city: number): Observable<Station[]> {
    return this.http.get<Station[]>(this.url + '/by-city',
      {
        params: new HttpParams()
          .set('city', city.toString())
      })
  }
}
