import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AujourdhuiComponent } from './aujourdhui.component';

describe('AujourdhuiComponent', () => {
  let component: AujourdhuiComponent;
  let fixture: ComponentFixture<AujourdhuiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AujourdhuiComponent]
    });
    fixture = TestBed.createComponent(AujourdhuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
