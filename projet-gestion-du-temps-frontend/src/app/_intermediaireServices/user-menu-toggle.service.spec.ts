import { TestBed } from '@angular/core/testing';

import { UserMenuToggleService } from './user-menu-toggle.service';

describe('UserMenuToggleService', () => {
  let service: UserMenuToggleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserMenuToggleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
