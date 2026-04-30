import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar-component/navbar-component';
import { FooterComponent } from './footer-component/footer-component';
import { HeroComponent } from "./hero-component/hero-component";
import { EspecialidadesComponent } from "./especialidades-component/especialidades-component";
import { DoctorDestacadoComponent } from "./doctor-destacado-component/doctor-destacado-component";
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent, HeroComponent, EspecialidadesComponent, DoctorDestacadoComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css'
})

export class HomeComponent {

  selectTime(event: Event) {
    const clicked = event.target as HTMLElement;
    document.querySelectorAll('.bc-time-btn').forEach(b => b.classList.remove('active'));
    clicked.classList.add('active');
  }

  scrollToReserva() {
    document.getElementById('reserva')?.scrollIntoView({ behavior: 'smooth' });
  }

  submitReserva() {
    alert('¡Reserva enviada! Recibirás un correo de confirmación pronto.');
  }
}
