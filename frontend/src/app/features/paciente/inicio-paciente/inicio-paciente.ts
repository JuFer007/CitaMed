import { Component, EventEmitter, Input, OnInit, Output, ChangeDetectorRef } from '@angular/core';
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
  proximasCount = 0;
  atendidasCount = 0;
  cargado = false;
  errorCarga = false;

  hoy = new Date();
  diasSemana = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
  meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];

  constructor(private portalService: PacientePortalService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.portalService.obtenerProximasCitas().subscribe({
      next: (data) => {
        this.proximaCita = data.length > 0 ? data[0] : null;
        this.proximasCount = data.length;
        this.totalCitas = data.length + this.atendidasCount;
        this.cargado = true;
        this.cdr.detectChanges();
      },
    });
    this.portalService.obtenerHistorialCitas().subscribe({
      next: (data) => {
        this.atendidasCount = data.length;
        this.totalCitas = this.proximasCount + data.length;
        this.cargado = true;
        this.cdr.detectChanges();
      },
      error: () => {
        this.cargado = true;
        this.errorCarga = true;
        this.cdr.detectChanges();
      },
    });
  }

  formatearFechaCita(fecha: string): string {
    if (!fecha) return '—';
    try {
      const d = new Date(fecha);
      return d.toLocaleDateString('es-PE', { day: 'numeric', month: 'long', year: 'numeric' });
    } catch {
      return fecha;
    }
  }

  formatearHoraCita(fecha: string): string {
    if (!fecha) return '';
    try {
      const d = new Date(fecha);
      return d.toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' });
    } catch {
      return '';
    }
  }
}
