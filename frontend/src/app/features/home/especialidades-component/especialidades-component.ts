import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import 'iconify-icon';

@Component({
  selector: 'app-especialidades',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './especialidades-component.html'
})
export class EspecialidadesComponent implements OnInit {

  especialidades: any[] = [];
  mostrarTodas = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/api/lading/especialidades')
      .subscribe({
        next: (data) => this.especialidades = data,
        error: (err) => console.error(err)
      });
  }

  get especialidadesVisibles(): any[] {
    return this.mostrarTodas
      ? this.especialidades
      : this.especialidades.slice(0, 6);
  }

  toggleVerMas(): void {
    this.mostrarTodas = !this.mostrarTodas;
  }

  obtenerIcono(nombre: string): string {

    const iconos: any = {

      'Medicina General': 'medical_services',
      'Cardiología': 'favorite',
      'Pediatría': 'child_care',
      'Ginecología y Obstetricia': 'pregnant_woman',
      'Traumatología y Ortopedia': 'orthopedics',
      'Neurología': 'neurology',
      'Dermatología': 'dermatology',
      'Endocrinología': 'biotech',
      'Gastroenterología': 'nutrition',
      'Oftalmología': 'visibility',
      'Psiquiatría': 'psychology',
      'Nefrología': 'water_drop',
      'Urología': 'health_and_safety',
      'Otorrinolaringología': 'hearing',
      'Reumatología': 'accessibility_new'

    };

    return iconos[nombre] || 'local_hospital';
  }
}
