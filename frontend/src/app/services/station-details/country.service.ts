import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Country} from "../../models/station-details/country";

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  private url:string="/api/countries"

  constructor(private http:HttpClient) { }

  public save(country:Country):Observable<Country>{
    return this.http.post<Country>(this.url + '/save',country);
  }

  public getAll():Observable<Country[]>{
    return this.http.get<Country[]>(this.url + '/get-all');
  }
}
