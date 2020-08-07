import {Injectable} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor(private fb: FormBuilder) {
  }

  public getLoginFormGroup(): FormGroup {
    return this.fb.group({
      email: ['',
        [
          Validators.required,
          Validators.maxLength(20),
          Validators.minLength(7),
          Validators.pattern(/^[\w-\.]+@[\w-]+\.[a-z]{2,4}$/i)
        ]],
      password: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
        ]
      ]
    })
  }

  public getUserFormGroup(): FormGroup {
    return this.fb.group({
      userName: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
          Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)]],
      userSurname: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
          Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)]],
      email: ['', [
        Validators.required,
        Validators.maxLength(20),
        Validators.pattern(/^[\w-\.]+@[\w-]+\.[a-z]{2,4}$/i)]],
      role: ['', [Validators.required]]
    })
  }

  public getTrainModelFormGroup(): FormGroup {
    return this.fb.group({
      trainModel: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)
        ]],
    })
  }

  public getTrainFormGroup(): FormGroup {
    return this.fb.group({
      trainModel: ['', [Validators.required]],
      dateOfCreation: ['', [Validators.required]]
    })
  }

  public getTripFormGroup(): FormGroup {
    return this.fb.group({
      trainModel: ['', [Validators.required]],
      train: ['', [Validators.required]],
      departureCountry: ['', [Validators.required]],
      arrivalCountry: ['', [Validators.required]],
      departureCity: ['', [Validators.required]],
      arrivalCity: ['', [Validators.required]],
      departureStation: ['', [Validators.required]],
      arrivalStation: ['', [Validators.required]],
    })
  }

  public getCountryFormGroup(): FormGroup {
    return this.fb.group({
      country: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)
        ]
      ]
    })
  }

  public getCityFormGroup(): FormGroup {
    return this.fb.group({
      country: ['', [Validators.required]],
      city: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)
        ]
      ]
    })
  }

  public getStationFormGroup(): FormGroup {
    return this.fb.group({
      city: ['', [Validators.required]],
      station: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)
        ]
      ]
    })
  }

  public getTaskFormGroup(): FormGroup {
    return this.fb.group({
      taskCreator: ['', [Validators.required]],
      dateOfCreation: ['', [Validators.required]],
      taskName: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50),
        Validators.pattern(/^[а-яА-ЯёЁa-zA-Z ]+$/)
      ]],
      status: ['', [Validators.required]],
      description: ['', [Validators.maxLength(100)]]
    })
  }
}
