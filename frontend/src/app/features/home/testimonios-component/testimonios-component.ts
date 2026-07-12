import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { TestimonioPublico } from '../../../model/Testimonio';
import 'iconify-icon';

@Component({
  selector: 'app-testimonios',
  standalone: true,
  imports: [CommonModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './testimonios-component.html',
  styleUrls: ['./testimonios-component.css']
})
export class TestimoniosComponent implements OnInit {

  testimonios: TestimonioPublico[] = [];

  fallbackTestimonios: TestimonioPublico[] = [
    { nombrePaciente: 'Juan P.', calificacion: 5, mensaje: 'Excelente atención médica. Me escucharon con paciencia y resolvieron todas mis dudas.', fechaCreacion: '' },
    { nombrePaciente: 'María T.', calificacion: 5, mensaje: 'Reservar cita fue muy sencillo. Puntualidad y trato profesional.', fechaCreacion: '' },
    { nombrePaciente: 'Carlos R.', calificacion: 5, mensaje: 'Diagnóstico rápido y tratamiento efectivo. Muy recomendado.', fechaCreacion: '' },
    { nombrePaciente: 'Ana L.', calificacion: 5, mensaje: 'Las instalaciones impecables y el personal muy amable.', fechaCreacion: '' }
  ];

  private colores = [
    'bg-[#36b2ac]/10 text-[#36b2ac]',
    'bg-blue-100 text-blue-700',
    'bg-emerald-100 text-emerald-700',
    'bg-pink-100 text-pink-700',
    'bg-amber-100 text-amber-700',
    'bg-violet-100 text-violet-700'
  ];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<TestimonioPublico[]>('http://localhost:8080/api/lading/testimonios')
      .subscribe({
        next: (data) => {
          this.testimonios = data.length > 0 ? data : this.fallbackTestimonios;
        },
        error: () => {
          this.testimonios = this.fallbackTestimonios;
        }
      });
  }

  getColor(index: number): string {
    return this.colores[index % this.colores.length];
  }

  getIniciales(nombre: string): string {
    return nombre.split('. ').map(p => p.charAt(0)).join('').substring(0, 2).toUpperCase();
  }

  getEstrellas(): number[] {
    return [1, 2, 3, 4, 5];
  }
}
