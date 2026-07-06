import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { Table, TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { SortEvent } from 'primeng/api';
import { ConsultorioService } from '../../../core/services/consultorio-service';
import { EspecialidadService } from '../../../core/services/especialidad-service';
import { Consultorio, ConsultorioPayload } from '../../../model/Consultorio';
import { Especialidad } from '../../../model/Especialidad';
import { GlobalToast } from '../../../core/services/global-toast';

@Component({
  selector: 'app-consultorios',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    DialogModule,
    ButtonModule,
    TableModule,
    InputTextModule,
  ],
  templateUrl: './consultorios-component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ConsultoriosComponent implements OnInit {
  constructor(
    private consultorioService: ConsultorioService,
    private especialidadService: EspecialidadService,
    private cdr: ChangeDetectorRef,
    private toast: GlobalToast,
  ) {}

  @ViewChild('dt') dt!: Table;
  initialValue: Consultorio[] = [];
  isSorted: boolean | null = null;
  private resetting = false;

  consultorios: Consultorio[] = [];
  consultoriosFiltrados: Consultorio[] = [];
  especialidades: Especialidad[] = [];
  terminoBusqueda = '';
  filtroDisponibilidad: string | null = null;
  mostrarModal = false;
  modoEdicion = false;
  consultorioEditandoId: number | null = null;

  form: ConsultorioPayload & { id?: number } = {
    numero: '',
    descripcion: '',
    especialidadId: 0,
    cupoMaximo: 3,
  };

  descripcionAuto = false;

  ngOnInit(): void {
    this.cargarConsultorios();
    this.cargarEspecialidades();
  }

  cargarConsultorios(): void {
    this.consultorioService.listar().subscribe({
      next: (data) => {
        this.consultorios = data;
        this.aplicarFiltros();
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudieron cargar los consultorios');
        this.cdr.markForCheck();
      },
    });
  }

  cargarEspecialidades(): void {
    this.especialidadService.obtenerTodas().subscribe({
      next: (data) => {
        this.especialidades = data;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudieron cargar las áreas');
        this.cdr.markForCheck();
      },
    });
  }

  aplicarFiltros(): void {
    let res = [...this.consultorios];

    if (this.terminoBusqueda.trim()) {
      const t = this.terminoBusqueda.toLowerCase();
      res = res.filter(
        (c) =>
          c.numero.toLowerCase().includes(t) ||
          c.descripcion.toLowerCase().includes(t),
      );
    }

    if (this.filtroDisponibilidad) {
      const disponible = this.filtroDisponibilidad === 'si';
      res = res.filter((c) => c.disponible === disponible);
    }

    this.consultoriosFiltrados = res;
    this.initialValue = [...res];
    this.isSorted = null;
    this.cdr.markForCheck();
  }

  limpiarFiltros(): void {
    this.terminoBusqueda = '';
    this.filtroDisponibilidad = null;
    this.aplicarFiltros();
  }

  abrirNuevo(): void {
    this.modoEdicion = false;
    this.consultorioEditandoId = null;
    this.form = { numero: '', descripcion: '', especialidadId: 0, cupoMaximo: 3 };
    this.descripcionAuto = false;
    this.mostrarModal = true;
    this.cdr.markForCheck();
  }

  editar(consultorio: Consultorio): void {
    this.modoEdicion = true;
    this.consultorioEditandoId = consultorio.id;
    this.descripcionAuto = false;
    this.form = {
      id: consultorio.id,
      numero: consultorio.numero,
      descripcion: consultorio.descripcion,
      especialidadId: consultorio.especialidad.id,
      cupoMaximo: consultorio.cupoMaximo ?? 3,
    };
    this.mostrarModal = true;
    this.cdr.markForCheck();
  }

  onAreaChange(): void {
    if (this.modoEdicion || !this.form.especialidadId) return;
    const esp = this.especialidades.find(e => e.id === this.form.especialidadId);
    if (esp) {
      this.form.descripcion = `Consultorio de ${esp.nombre}`;
      this.descripcionAuto = true;
      this.cdr.markForCheck();
    }
  }

  guardar(): void {
    if (!this.form.numero?.trim()) {
      this.toast.warn('Ingrese el número del consultorio');
      return;
    }
    if (this.form.numero.trim().length < 2) {
      this.toast.warn('El número debe tener al menos 2 caracteres');
      return;
    }
    if (!this.form.descripcion?.trim()) {
      this.toast.warn('Ingrese la descripción');
      return;
    }
    if (this.form.descripcion.trim().length < 5) {
      this.toast.warn('La descripción debe tener al menos 5 caracteres');
      return;
    }
    if (!this.form.especialidadId) {
      this.toast.warn('Seleccione un área');
      return;
    }
    if (this.form.cupoMaximo == null || this.form.cupoMaximo < 1) {
      this.toast.warn('El cupo mínimo es 1 médico');
      return;
    }
    if (this.form.cupoMaximo > 10) {
      this.toast.warn('El cupo máximo es 10 médicos');
      return;
    }

    const dto: ConsultorioPayload = {
      numero: this.form.numero.trim(),
      descripcion: this.form.descripcion.trim(),
      especialidadId: this.form.especialidadId,
      cupoMaximo: this.form.cupoMaximo || 3,
    };

    if (this.modoEdicion && this.consultorioEditandoId != null) {
      this.consultorioService.actualizar(this.consultorioEditandoId, dto).subscribe({
        next: (msg) => {
          this.toast.success(msg);
          this.cerrarModal();
        },
        error: (err) => {
          this.toast.error(err.error || 'Error al actualizar');
          this.cdr.markForCheck();
        },
      });
    } else {
      this.consultorioService.crear(dto).subscribe({
        next: (msg) => {
          this.toast.success(msg);
          this.cerrarModal();
        },
        error: (err) => {
          this.toast.error(err.error || 'Error al registrar');
          this.cdr.markForCheck();
        },
      });
    }
  }

  toggleDisponible(consultorio: Consultorio): void {
    this.consultorioService.toggleDisponible(consultorio.id).subscribe({
      next: (msg) => {
        this.toast.success(msg);
        this.cargarConsultorios();
      },
      error: () => {
        this.toast.error('Error al cambiar estado');
        this.cdr.markForCheck();
      },
    });
  }

  private cerrarModal(): void {
    this.mostrarModal = false;
    this.cargarConsultorios();
  }

  customSort(event: SortEvent): void {
    if (this.resetting) return;

    if (this.isSorted == null || this.isSorted === undefined) {
      this.isSorted = true;
      this.sortTableData(event);
    } else if (this.isSorted === true) {
      this.isSorted = false;
      this.sortTableData(event);
    } else {
      this.isSorted = null;
      this.resetting = true;
      this.consultoriosFiltrados = [...this.initialValue];
      this.dt.reset();
      setTimeout(() => {
        this.resetting = false;
      }, 0);
    }
  }

  private sortTableData(event: SortEvent): void {
    this.consultoriosFiltrados.sort((data1, data2) => {
      const value1 = (data1 as any)[event.field!];
      const value2 = (data2 as any)[event.field!];
      let result: number;

      if (value1 == null && value2 != null) result = -1;
      else if (value1 != null && value2 == null) result = 1;
      else if (value1 == null && value2 == null) result = 0;
      else if (typeof value1 === 'string' && typeof value2 === 'string')
        result = value1.localeCompare(value2);
      else result = value1 < value2 ? -1 : value1 > value2 ? 1 : 0;

      return event.order! * result;
    });
  }
}
