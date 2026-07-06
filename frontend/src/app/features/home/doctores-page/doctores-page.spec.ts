import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctoresPage } from './doctores-page';

describe('DoctoresPage', () => {
  let component: DoctoresPage;
  let fixture: ComponentFixture<DoctoresPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctoresPage],
    }).compileComponents();

    fixture = TestBed.createComponent(DoctoresPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
