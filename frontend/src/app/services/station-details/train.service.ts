import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {City} from "../../models/station-details/city";
import {Observable} from "rxjs";
import {Train} from "../../models/station-details/train";

@Injectable({
  providedIn: 'root'
})
export class TrainService {

  private url: string = "/api/trains";

  constructor(private http: HttpClient) {
  }

  public save(train: Train): Observable<Train> {
    return this.http.post<Train>(this.url + '/save', train);
  }

  public getAll(): Observable<Train[]> {
    return this.http.get<Train[]>(this.url + '/get-all');
  }

  public getAllByModel(model: number): Observable<Train[]> {
    return this.http.get<Train[]>(
      this.url + '/by-model',
      {
        params: new HttpParams()
          .set('model', model.toString())
      })
  }
}
