import { TestBed } from '@angular/core/testing';

import { AujourdhuiService } from './aujourdhui.service';

describe('AujourdhuiService', () => {
  let service: AujourdhuiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AujourdhuiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
