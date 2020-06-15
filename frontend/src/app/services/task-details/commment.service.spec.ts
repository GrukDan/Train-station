import { TestBed } from '@angular/core/testing';

import { CommmentService } from './commment.service';

describe('CommmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommmentService = TestBed.get(CommmentService);
    expect(service).toBeTruthy();
  });
});
