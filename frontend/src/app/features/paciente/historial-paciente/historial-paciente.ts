import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacientePortalService, PortalHistorial } from '../../../core/services/paciente-portal-service';

@Component({
  selector: 'app-historial-paciente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historial-paciente.html',
  styleUrl: './historial-paciente.css',
})
export class HistorialPacienteComponent implements OnInit {
  items: PortalHistorial[] = [];

  constructor(private portalService: PacientePortalService) {}

  ngOnInit(): void {
    this.portalService.obtenerHistorialClinico().subscribe({
      next: (data) => { this.items = data; },
    });
  }
}
