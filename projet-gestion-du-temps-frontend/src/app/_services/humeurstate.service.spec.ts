import { TestBed } from '@angular/core/testing';

import { HumeurstateService } from './humeurstate.service';

describe('HumeurstateService', () => {
  let service: HumeurstateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HumeurstateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
