import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import 'iconify-icon';

@Component({
  selector: 'app-testimonios',
  standalone: true,
  imports: [CommonModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './testimonios-component.html',
  styleUrls: ['./testimonios-component.css']
})
export class TestimoniosComponent {

  testimonios = [
    {
      nombre: 'Juan Pérez',
      tipo: 'Paciente frecuente',
      iniciales: 'JP',
      color: 'bg-[#36b2ac]/10 text-[#36b2ac]',
      mensaje: 'Excelente atención médica. Me escucharon con paciencia y resolvieron todas mis dudas.'
    },
    {
      nombre: 'María Torres',
      tipo: 'Chequeo general',
      iniciales: 'MT',
      color: 'bg-blue-100 text-blue-700',
      mensaje: 'Reservar cita fue muy sencillo. Puntualidad y trato profesional.'
    },
    {
      nombre: 'Carlos Rojas',
      tipo: 'Consulta general',
      iniciales: 'CR',
      color: 'bg-emerald-100 text-emerald-700',
      mensaje: 'Diagnóstico rápido y tratamiento efectivo. Muy recomendado.'
    },
    {
      nombre: 'Ana López',
      tipo: 'Paciente nueva',
      iniciales: 'AL',
      color: 'bg-pink-100 text-pink-700',
      mensaje: 'Las instalaciones impecables y el personal muy amable.'
    }
  ];

}
