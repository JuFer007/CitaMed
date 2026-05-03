import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialMedicoC } from './historial-medico-c';

describe('HistorialMedicoC', () => {
  let component: HistorialMedicoC;
  let fixture: ComponentFixture<HistorialMedicoC>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistorialMedicoC],
    }).compileComponents();

    fixture = TestBed.createComponent(HistorialMedicoC);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
