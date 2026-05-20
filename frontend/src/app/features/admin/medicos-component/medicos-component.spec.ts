import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MedicosComponent } from './medicos-component';

describe('MedicosComponent', () => {
  let component: MedicosComponent;
  let fixture: ComponentFixture<MedicosComponent>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [
        MedicosComponent,
        HttpClientTestingModule
      ]

    }).compileComponents();
    fixture = TestBed.createComponent(MedicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});