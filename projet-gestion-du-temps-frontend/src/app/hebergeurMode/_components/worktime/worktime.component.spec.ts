import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorktimeComponent } from './worktime.component';

describe('WorktimeComponent', () => {
  let component: WorktimeComponent;
  let fixture: ComponentFixture<WorktimeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorktimeComponent]
    });
    fixture = TestBed.createComponent(WorktimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
