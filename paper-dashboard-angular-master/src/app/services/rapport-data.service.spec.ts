import { TestBed } from '@angular/core/testing';

import { RapportDataService } from './rapport-data.service';

describe('RapportDataService', () => {
  let service: RapportDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RapportDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
