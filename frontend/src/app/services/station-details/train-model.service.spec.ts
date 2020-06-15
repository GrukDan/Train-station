import { TestBed } from '@angular/core/testing';

import { TrainModelService } from './train-model.service';

describe('TrainModelService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainModelService = TestBed.get(TrainModelService);
    expect(service).toBeTruthy();
  });
});
