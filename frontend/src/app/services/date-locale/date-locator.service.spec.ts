import { TestBed } from '@angular/core/testing';

import { DateLocatorService } from './date-locator.service';

describe('DateLocatorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DateLocatorService = TestBed.get(DateLocatorService);
    expect(service).toBeTruthy();
  });
});
