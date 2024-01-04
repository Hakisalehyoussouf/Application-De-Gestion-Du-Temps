import { TestBed } from '@angular/core/testing';

import { DateFinStateService } from './date-fin-state.service';

describe('DateFinStateService', () => {
  let service: DateFinStateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DateFinStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
