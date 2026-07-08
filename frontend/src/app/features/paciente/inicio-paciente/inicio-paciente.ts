import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacientePortalService, PortalPerfil, PortalCita } from '../../../core/services/paciente-portal-service';

@Component({
  selector: 'app-inicio-paciente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './inicio-paciente.html',
  styleUrl: './inicio-paciente.css',
})
export class InicioPacienteComponent implements OnInit {
  @Input() perfil: PortalPerfil | null = null;
  @Output() cambiarTab = new EventEmitter<string>();

  proximaCita: PortalCita | null = null;
  totalCitas = 0;
  cargado = false;

  hoy = new Date();
  diasSemana = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
  meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];

  constructor(private portalService: PacientePortalService) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.portalService.obtenerProximasCitas().subscribe({
      next: (data) => {
        this.proximaCita = data.length > 0 ? data[0] : null;
        this.totalCitas = data.length;
        this.cargado = true;
      },
      error: () => { this.cargado = true; },
    });
  }
}
