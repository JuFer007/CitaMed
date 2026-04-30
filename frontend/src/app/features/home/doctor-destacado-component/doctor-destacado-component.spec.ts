import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorDestacadoComponent } from './doctor-destacado-component';

describe('DoctorDestacadoComponent', () => {
  let component: DoctorDestacadoComponent;
  let fixture: ComponentFixture<DoctorDestacadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorDestacadoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DoctorDestacadoComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
