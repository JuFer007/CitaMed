import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { AuthService } from '../../../core/services/auth-service';
import { CitaService } from '../../../core/services/cita-service';
import { DiagnosticoService } from '../../../core/services/diagnostico-service';
import { CitaDetalle, EstadoCita } from '../../../model/Cita';
import { DiagnosticoDTO } from '../../../model/Diagnostico';
import { GlobalToast } from '../../../core/services/global-toast';

type FiltroVista = 'PENDIENTES' | 'ATENDIDAS' | 'TODAS';

@Component({
  selector: 'app-diagnosticos-component',
  standalone: true,
  imports: [CommonModule, FormsModule, DialogModule],
  templateUrl: './diagnosticos-component.html',
  styleUrl: './diagnosticos-component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DiagnosticosComponent implements OnInit {
  todas: CitaDetalle[] = [];
  filtrados: CitaDetalle[] = [];
  loading = false;
  terminoBusqueda = '';
  vista: FiltroVista = 'PENDIENTES';
  generandoPdfId: number | null = null;

  mostrarModal = false;
  citaAtendiendo: CitaDetalle | null = null;
  guardando = false;
  modoEdicion = false;
  cargandoDiagnostico = false;

  form: DiagnosticoDTO = {
    citaId: 0,
    enfermedad: '',
    descripcion: '',
    receta: '',
    indicaciones: '',
  };

  atendidoExitoso = false;

  readonly EstadoCita = EstadoCita;

  constructor(
    private authService: AuthService,
    private citaService: CitaService,
    private diagnosticoService: DiagnosticoService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  get esSoloLectura(): boolean {
    return this.authService.isAdmin() && !this.authService.isMedico();
  }

  ngOnInit(): void {
    this.cargarTodas();
  }

  cargarTodas(): void {
    this.loading = true;
    this.cdr.markForCheck();
    this.citaService.obtenerTodas().subscribe({
      next: (data) => {
        this.todas = data;
        this.aplicarFiltros();
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudieron cargar las citas');
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }

  setVista(v: FiltroVista): void {
    this.vista = v;
    this.aplicarFiltros();
  }

  aplicarFiltros(): void {
    let res = [...this.todas];

    if (this.vista === 'PENDIENTES') {
      res = res.filter((c) => c.estado === EstadoCita.PROGRAMADA);
    } else if (this.vista === 'ATENDIDAS') {
      res = res.filter((c) => c.estado === EstadoCita.ATENDIDA);
    }

    const t = this.terminoBusqueda.toLowerCase().trim();
    if (t) {
      res = res.filter((c) => {
        const pac = `${c.pacienteNombre} ${c.pacienteApellidoPaterno} ${c.pacienteApellidoMaterno}`.toLowerCase();
        const med = `${c.medicoNombre} ${c.medicoApellidoPaterno} ${c.medicoApellidoMaterno}`.toLowerCase();
        return pac.includes(t) || med.includes(t) || c.pacienteDni.includes(t);
      });
    }

    this.filtrados = res;
    this.cdr.markForCheck();
  }

  abrirAtender(cita: CitaDetalle): void {
    this.modoEdicion = false;
    this.citaAtendiendo = cita;
    this.form = {
      citaId: cita.id,
      enfermedad: '',
      descripcion: '',
      receta: '',
      indicaciones: '',
    };
    this.atendidoExitoso = false;
    this.mostrarModal = true;
    this.cdr.markForCheck();
  }

  abrirEditar(cita: CitaDetalle): void {
    this.modoEdicion = true;
    this.citaAtendiendo = cita;
    this.cargandoDiagnostico = true;
    this.atendidoExitoso = false;
    this.mostrarModal = true;
    this.cdr.markForCheck();

    this.diagnosticoService.obtenerPorCita(cita.id).subscribe({
      next: (diag) => {
        this.form = {
          citaId: cita.id,
          enfermedad: diag.enfermedad || '',
          descripcion: diag.descripcion || '',
          receta: diag.receta || '',
          indicaciones: diag.indicaciones || '',
        };
        this.cargandoDiagnostico = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudo cargar el diagnóstico');
        this.cargandoDiagnostico = false;
        this.mostrarModal = false;
        this.cdr.markForCheck();
      },
    });
  }

  get formValido(): boolean {
    return this.form.enfermedad.trim().length > 0;
  }

  guardar(): void {
    if (!this.formValido) {
      this.toast.warn('Debe ingresar la enfermedad o diagnóstico');
      return;
    }
    this.guardando = true;
    this.cdr.markForCheck();

    if (this.modoEdicion) {
      this.diagnosticoService.actualizar(this.form.citaId, this.form).subscribe({
        next: () => {
          this.guardando = false;
          this.cerrarModal();
          this.toast.success('Diagnóstico actualizado correctamente');
          this.cargarTodas();
          this.cdr.markForCheck();
        },
        error: (err) => {
          this.guardando = false;
          this.toast.error(err.error || 'Error al actualizar el diagnóstico');
          this.cdr.markForCheck();
        },
      });
    } else {
      this.diagnosticoService.atender(this.form).subscribe({
        next: () => {
          this.guardando = false;
          this.cerrarModal();
          this.toast.success('Paciente atendido correctamente');
          this.cargarTodas();
          this.cdr.markForCheck();
        },
        error: (err) => {
          this.guardando = false;
          this.toast.error(err.error || 'Error al registrar la atención');
          this.cdr.markForCheck();
        },
      });
    }
  }

  descargarReceta(cita: CitaDetalle): void {
    this.generandoPdfId = cita.id;
    this.cdr.markForCheck();

    this.diagnosticoService.descargarReceta(cita.id).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        window.open(url, '_blank');
        this.generandoPdfId = null;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('Error al generar el PDF de receta');
        this.generandoPdfId = null;
        this.cdr.markForCheck();
      },
    });
  }

  cerrarModal(): void {
    this.mostrarModal = false;
    this.citaAtendiendo = null;
    this.cdr.markForCheck();
  }

  nombrePaciente(cita: CitaDetalle): string {
    return `${cita.pacienteNombre} ${cita.pacienteApellidoPaterno} ${cita.pacienteApellidoMaterno}`;
  }

  nombreMedico(cita: CitaDetalle): string {
    return `${cita.medicoNombre} ${cita.medicoApellidoPaterno} ${cita.medicoApellidoMaterno}`;
  }

  getInitials(cita: CitaDetalle): string {
    return ((cita.pacienteNombre?.charAt(0) || '') + (cita.pacienteApellidoPaterno?.charAt(0) || '')).toUpperCase();
  }

  estadoClass(estado: EstadoCita): string {
    const map: Record<EstadoCita, string> = {
      [EstadoCita.PROGRAMADA]: 'dx-badge-programada',
      [EstadoCita.ATENDIDA]: 'dx-badge-atendida',
      [EstadoCita.CANCELADA]: 'dx-badge-cancelada',
      [EstadoCita.NO_ASISTIO]: 'dx-badge-noasistio',
    };
    return map[estado] ?? '';
  }
}
