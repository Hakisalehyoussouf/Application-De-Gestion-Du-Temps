import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeTemplateComponent } from './employee-template.component';

describe('EmployeeTemplateComponent', () => {
  let component: EmployeeTemplateComponent;
  let fixture: ComponentFixture<EmployeeTemplateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeeTemplateComponent]
    });
    fixture = TestBed.createComponent(EmployeeTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
