import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmaciaPage } from './farmacia-page';

describe('FarmaciaPage', () => {
  let component: FarmaciaPage;
  let fixture: ComponentFixture<FarmaciaPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FarmaciaPage],
    }).compileComponents();

    fixture = TestBed.createComponent(FarmaciaPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
