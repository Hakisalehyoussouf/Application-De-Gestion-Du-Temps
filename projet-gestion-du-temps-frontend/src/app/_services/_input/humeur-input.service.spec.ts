import { TestBed } from '@angular/core/testing';

import { HumeurInputService } from './humeur-input.service';

describe('HumeurInputService', () => {
  let service: HumeurInputService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HumeurInputService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
