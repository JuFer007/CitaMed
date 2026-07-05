import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Table, TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { DatePickerModule } from 'primeng/datepicker';
import { SortEvent } from 'primeng/api';
import { Empleado, EmpleadoDTO } from '../../../model/Empleado';
import { EmpleadoService } from '../../../core/services/empleado-service';
import { GlobalToast } from '../../../core/services/global-toast';

@Component({
  selector: 'app-empleado-component',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TableModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    SelectModule,
    DatePickerModule,
  ],
  templateUrl: './empleado-component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmpleadoComponent implements OnInit {
  @ViewChild('dt') dt!: Table;
  initialValue: Empleado[] = [];
  isSorted: boolean | null = null;
  private resetting = false;

  empleados: Empleado[] = [];
  empleadosFiltrados: Empleado[] = [];
  cargando = true;
  error = '';

  terminoBusqueda = '';

  mostrarModal = false;
  editando = false;
  empleadoEditandoId: number | null = null;
  form: EmpleadoDTO & { genero: string; rol: string } = {
    nombre: '', apellidoPaterno: '', apellidoMaterno: '', dni: '',
    telefono: '', direccion: '', email: '', fechaNacimiento: '',
    genero: 'MASCULINO', salario: 0,
    userName: '', password: '', rol: 'RECEPCIONISTA',
  };

  fechaNacimientoDate: Date | null = null;

  mostrarConfirmarEstado = false;
  empleadoCambiandoEstado: Empleado | null = null;

  readonly generos = ['MASCULINO', 'FEMENINO'];
  readonly roles = ['ADMIN', 'MEDICO', 'RECEPCIONISTA'];

  constructor(
    private empleadoService: EmpleadoService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.cargarEmpleados();
  }

  cargarEmpleados(): void {
    this.cargando = true;
    this.empleadoService.obtenerTodas().subscribe({
      next: (data) => {
        this.empleados = data;
        this.aplicarFiltros();
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.cargando = false;
        this.error = 'Error al cargar los empleados';
        this.cdr.markForCheck();
      },
    });
  }

  aplicarFiltros(): void {
    let res = [...this.empleados];
    const t = this.terminoBusqueda.toLowerCase().trim();
    if (t) {
      res = res.filter((e) =>
        `${e.nombre} ${e.apellidoPaterno} ${e.apellidoMaterno}`.toLowerCase().includes(t) ||
        e.dni.includes(t) ||
        e.email.toLowerCase().includes(t) ||
        e.telefono.includes(t) ||
        e.usuario.userName.toLowerCase().includes(t)
      );
    }
    this.empleadosFiltrados = res;
    this.initialValue = [...res];
    this.isSorted = null;
    this.cdr.markForCheck();
  }

  limpiarFiltros(): void {
    this.terminoBusqueda = '';
    this.aplicarFiltros();
  }

  customSort(event: SortEvent): void {
    if (this.resetting) return;
    if (this.isSorted == null) {
      this.isSorted = true;
      this.sortTableData(event);
    } else if (this.isSorted === true) {
      this.isSorted = false;
      this.sortTableData(event);
    } else {
      this.isSorted = null;
      this.resetting = true;
      this.empleadosFiltrados = [...this.initialValue];
      this.dt.reset();
      setTimeout(() => { this.resetting = false; }, 0);
    }
  }

  private sortTableData(event: SortEvent): void {
    this.empleadosFiltrados.sort((data1, data2) => {
      const value1 = (data1 as any)[event.field!];
      const value2 = (data2 as any)[event.field!];
      let result: number;
      if (value1 == null && value2 != null) result = -1;
      else if (value1 != null && value2 == null) result = 1;
      else if (value1 == null && value2 == null) result = 0;
      else if (typeof value1 === 'string' && typeof value2 === 'string')
        result = value1.localeCompare(value2);
      else if (typeof value1 === 'boolean' && typeof value2 === 'boolean')
        result = value1 === value2 ? 0 : value1 ? 1 : -1;
      else result = value1 < value2 ? -1 : value1 > value2 ? 1 : 0;
      return event.order! * result;
    });
  }

  abrirNuevo(): void {
    this.editando = false;
    this.empleadoEditandoId = null;
    this.form = {
      nombre: '', apellidoPaterno: '', apellidoMaterno: '', dni: '',
      telefono: '', direccion: '', email: '', fechaNacimiento: '',
      genero: 'MASCULINO', salario: 0,
      userName: '', password: '', rol: 'RECEPCIONISTA',
    };
    this.fechaNacimientoDate = null;
    this.mostrarModal = true;
  }

  abrirEditar(empleado: Empleado): void {
    this.editando = true;
    this.empleadoEditandoId = empleado.id!;
    this.form = {
      nombre: empleado.nombre,
      apellidoPaterno: empleado.apellidoPaterno,
      apellidoMaterno: empleado.apellidoMaterno,
      dni: empleado.dni,
      telefono: empleado.telefono,
      direccion: empleado.direccion || '',
      email: empleado.email,
      fechaNacimiento: empleado.fechaNacimiento,
      genero: empleado.genero,
      salario: empleado.salario,
      userName: empleado.usuario.userName,
      password: '',
      rol: empleado.usuario.rol,
    };
    this.fechaNacimientoDate = empleado.fechaNacimiento ? new Date(empleado.fechaNacimiento + 'T00:00:00') : null;
    this.mostrarModal = true;
  }

  guardar(): void {
    if (!this.form.nombre.trim() || !this.form.apellidoPaterno.trim()) {
      this.toast.warn('Nombre y apellido son obligatorios');
      return;
    }
    if (!this.form.dni || this.form.dni.length !== 8) {
      this.toast.warn('El DNI debe tener 8 dígitos');
      return;
    }
    if (!this.editando) {
      if (!this.form.userName.trim()) {
        this.toast.warn('El nombre de usuario es obligatorio');
        return;
      }
      if (!this.form.password || this.form.password.length < 6) {
        this.toast.warn('La contraseña debe tener al menos 6 caracteres');
        return;
      }
    }

    if (this.fechaNacimientoDate) {
      this.form.fechaNacimiento = this.fechaNacimientoDate.toISOString().split('T')[0];
    }

    if (this.editando) {
      this.empleadoService.actualizar(this.empleadoEditandoId!, this.form).subscribe({
        next: (res) => {
          this.toast.success(res);
          this.mostrarModal = false;
          this.cargarEmpleados();
        },
        error: (err) => this.toast.error(err?.error || 'Error al actualizar el empleado'),
      });
    } else {
      this.empleadoService.crear(this.form).subscribe({
        next: (res) => {
          this.toast.success(res);
          this.mostrarModal = false;
          this.cargarEmpleados();
        },
        error: (err) => this.toast.error(err?.error || 'Error al registrar el empleado'),
      });
    }
  }

  confirmarCambiarEstado(empleado: Empleado): void {
    this.empleadoCambiandoEstado = empleado;
    this.mostrarConfirmarEstado = true;
  }

  toggleEstado(): void {
    if (!this.empleadoCambiandoEstado) return;
    this.empleadoService.cambiarEstado(this.empleadoCambiandoEstado.id!).subscribe({
      next: (res) => {
        this.toast.success(res);
        this.mostrarConfirmarEstado = false;
        this.empleadoCambiandoEstado = null;
        this.cargarEmpleados();
      },
      error: (err) => this.toast.error(err?.error || 'Error al cambiar el estado'),
    });
  }

  nombreCompleto(e: Empleado): string {
    return `${e.nombre} ${e.apellidoPaterno} ${e.apellidoMaterno}`;
  }

  obtenerClaseGenero(genero: string): string {
    return genero === 'FEMENINO'
      ? 'bg-pink-100 text-pink-700'
      : genero === 'MASCULINO'
        ? 'bg-blue-100 text-blue-700'
        : 'bg-gray-100 text-gray-700';
  }

  obtenerClaseRol(rol: string): string {
    const map: Record<string, string> = {
      ADMIN: 'bg-red-100 text-red-800',
      MEDICO: 'bg-blue-100 text-blue-800',
      RECEPCIONISTA: 'bg-amber-100 text-amber-800',
    };
    return map[rol] ?? '';
  }

  obtenerAvatar(e: Empleado): string {
    return `${e.nombre.charAt(0)}${e.apellidoPaterno.charAt(0)}`;
  }
}
