import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-footer-portal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './footer-portal.html',
  styleUrl: './footer-portal.css',
})
export class FooterPortalComponent {
  @Output() cambiarTab = new EventEmitter<string>();

  links = [
    { tab: 'inicio', label: 'Inicio', icon: 'home' },
    { tab: 'reservar', label: 'Reservar cita', icon: 'add_circle' },
    { tab: 'citas', label: 'Mis citas', icon: 'calendar_month' },
    { tab: 'historial', label: 'Historial clínico', icon: 'history' },
    { tab: 'pagos', label: 'Pagos', icon: 'payments' },
    { tab: 'testimonios', label: 'Mis reseñas', icon: 'rate_review' },
    { tab: 'perfil', label: 'Mi perfil', icon: 'person' },
  ];

  ir(tab: string): void {
    this.cambiarTab.emit(tab);
  }
}
