import { Injectable } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor(private fb: FormBuilder) { }

  public getTrainFormGroup():FormGroup {
    return  this.fb.group({
      trainModel:['',[Validators.nullValidator]],
      dateOfCreation:['',[Validators.required]]
    })
  }
}
