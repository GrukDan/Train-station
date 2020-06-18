import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Country} from "../../models/station-details/country";
import {Observable} from "rxjs";
import {TrainModel} from "../../models/station-details/train-model";

@Injectable({
  providedIn: 'root'
})
export class TrainModelService {

  private url:string="/api/train-models"

  constructor(private http:HttpClient) { }

  public save(trainModel:TrainModel):Observable<TrainModel>{
    return this.http.post<TrainModel>(this.url + '/save',trainModel);
  }

  public getAll():Observable<TrainModel[]>{
    return this.http.get<TrainModel[]>(this.url + '/get-all');
  }

}
