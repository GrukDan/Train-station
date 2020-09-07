import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Country} from "../../models/station-details/country";
import {Observable} from "rxjs";
import {City} from "../../models/station-details/city";

@Injectable({
  providedIn: 'root'
})
export class CityService {

  private url: string = "/api/cities"

  constructor(private http: HttpClient) {
  }

  public save(city: City): Observable<City> {
    return this.http.post<City>(this.url + '/save', city);
  }

  public getAll(): Observable<City[]> {
    return this.http.get<City[]>(this.url + '/get-all');
  }

  public getAllByCountry(country: number): Observable<City[]> {
    return this.http.get<City[]>(this.url + '/by-country',
      {
        params: new HttpParams()
          .set('country', country.toString())
      })
  }

  public delete(idCity:number):Observable<void>{
    return this.http.delete<void>(this.url + '/' + idCity.toString());
  }
}
