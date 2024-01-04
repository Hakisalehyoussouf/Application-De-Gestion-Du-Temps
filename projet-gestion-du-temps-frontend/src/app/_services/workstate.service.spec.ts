import { TestBed } from '@angular/core/testing';

import { WorkstateService } from './workstate.service';

describe('WorkstateService', () => {
  let service: WorkstateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkstateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
