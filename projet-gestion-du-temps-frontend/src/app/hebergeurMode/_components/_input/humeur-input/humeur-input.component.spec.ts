import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HumeurInputComponent } from './humeur-input.component';

describe('HumeurInputComponent', () => {
  let component: HumeurInputComponent;
  let fixture: ComponentFixture<HumeurInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HumeurInputComponent]
    });
    fixture = TestBed.createComponent(HumeurInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
