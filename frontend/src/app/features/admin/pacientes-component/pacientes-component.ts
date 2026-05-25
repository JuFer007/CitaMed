import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { LucideAngularModule, User } from 'lucide-angular';
import { PacienteService } from '../../../core/services/paciente-service';
import { Paciente, PacienteDTO, PageResponse } from '../../../model/Paciente';
import { GlobalToast } from '../../../core/services/global-toast';

@Component({
  selector: 'app-pacientes-component',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, DialogModule, ButtonModule, TableModule, PaginatorModule, LucideAngularModule],
  templateUrl: './pacientes-component.html',
  styleUrls: ['./pacientes-component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PacientesComponent implements OnInit {
  User = User;
  pacientes: Paciente[] = [];
  pacientesFiltrados: Paciente[] = [];
  totalRecords = 0;
  page = 0;
  size = 10;
  loading = false;
  mostrarModal = false;
  modoEdicion = false;
  pacienteEditando: Paciente | null = null;
  terminoBusqueda = '';
  nuevoPaciente: PacienteDTO = {
    nombre: '',
    apellidoPaterno: '',
    apellidoMaterno: '',
    dni: '',
    telefono: '',
    direccion: '',
    email: '',
    fechaNacimiento: null as any,
    genero: null as any,
    grupoSanguineo: null as any,
  };

  constructor(
    private pacienteService: PacienteService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.obtenerPacientes();
  }

  filtrar(): void {
    const texto = (this.terminoBusqueda || '').toLowerCase().trim();
    if (!texto) {
      this.pacientesFiltrados = [...this.pacientes];
      this.cdr.markForCheck();
      return;
    }

    this.pacientesFiltrados = this.pacientes.filter((p) => {
      const nombreCompleto = `${p.nombre} ${p.apellidoPaterno} ${p.apellidoMaterno}`.toLowerCase();
      const coincideTexto =
        nombreCompleto.includes(texto) ||
        (p.dni ?? '').toLowerCase().includes(texto) ||
        (p.email ?? '').toLowerCase().includes(texto) ||
        (p.telefono ?? '').toLowerCase().includes(texto);
      return coincideTexto;
    });
    this.cdr.markForCheck();
  }

  resetFormulario(): void {
    this.nuevoPaciente = {
      nombre: '',
      apellidoPaterno: '',
      apellidoMaterno: '',
      dni: '',
      telefono: '',
      direccion: '',
      email: '',
      fechaNacimiento: null as any,
      genero: null as any,
      grupoSanguineo: null as any,
    };
    this.pacienteEditando = null;
    this.modoEdicion = false;
    this.cdr.markForCheck();
  }

  finalizarGuardado(): void {
    this.obtenerPacientes();
    this.resetFormulario();
    this.mostrarModal = false;
    this.cdr.markForCheck();
  }

  loadPage(page: number): void {
    this.loading = true;
    this.pacienteService.listar(page, this.size).subscribe({
      next: (res: PageResponse<Paciente> | any) => {
        if (res && res.content) {
          this.pacientes = res.content;
          this.totalRecords = res.totalElements;
        } else if (Array.isArray(res)) {
          this.pacientes = res;
          this.totalRecords = res.length;
        } else if (res && Array.isArray(res.content) === false && res instanceof Object) {
          // if backend returned list wrapped as PageResponse incorrectly
          this.pacientes = (res as any).content || [];
          this.totalRecords = (res as any).totalElements || this.pacientes.length;
        }
        this.page = page;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.toast.error('Error cargando pacientes');
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }

  obtenerPacientes(): void {
    this.loading = true;
    this.pacienteService.listarTodos().subscribe({
      next: (data: Paciente[]) => {
        this.pacientes = data;
        this.pacientesFiltrados = [...data];
        this.totalRecords = data.length;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.toast.error('No se pudieron cargar los pacientes');
        this.loading = false;
        this.cdr.markForCheck();
      }
    });
  }

  onPage(event: any): void {
    const pageFromProp = event && (event.page ?? null);
    if (pageFromProp !== null && pageFromProp !== undefined) {
      this.loadPage(pageFromProp);
      return;
    }
    const first = Number(event?.first ?? 0);
    const rows = Number(event?.rows ?? this.size ?? 10);
    const page = rows > 0 ? Math.floor(first / rows) : 0;
    this.loadPage(page);
  }

  openNuevo(): void {
    this.resetFormulario();
    this.mostrarModal = true;
    this.cdr.markForCheck();
  }

  openEditar(p: Paciente): void {
    this.modoEdicion = true;
    this.pacienteEditando = p;
    this.nuevoPaciente = {
      nombre: p.nombre,
      apellidoPaterno: p.apellidoPaterno,
      apellidoMaterno: p.apellidoMaterno,
      dni: p.dni,
      telefono: p.telefono,
      direccion: p.direccion || '',
      email: p.email || '',
      fechaNacimiento: p.fechaNacimiento || null as any,
      genero: p.genero || null as any,
      grupoSanguineo: p.grupoSanguineo || null as any,
    };
    this.mostrarModal = true;
    this.cdr.markForCheck();
  }

  guardar(): void {
    if (!this.nuevoPaciente.nombre?.trim()) {
      this.toast.warn('Ingrese el nombre');
      return;
    }
    if (!/^[0-9]{8}$/.test(this.nuevoPaciente.dni)) {
      this.toast.warn('DNI inválido');
      return;
    }
    if (!/^[0-9]{9}$/.test(this.nuevoPaciente.telefono)) {
      this.toast.warn('Teléfono inválido');
      return;
    }

    // Backend required fields validations
    if (!this.nuevoPaciente.direccion || !this.nuevoPaciente.direccion.trim()) {
      this.toast.warn('Ingrese la dirección');
      return;
    }
    if (!this.nuevoPaciente.fechaNacimiento) {
      this.toast.warn('Seleccione la fecha de nacimiento');
      return;
    }
    if (!this.nuevoPaciente.genero) {
      this.toast.warn('Seleccione el género');
      return;
    }
    if (!this.nuevoPaciente.grupoSanguineo) {
      this.toast.warn('Seleccione el grupo sanguíneo');
      return;
    }

    if (this.modoEdicion && this.pacienteEditando && this.pacienteEditando.id) {
      this.pacienteService.modificar(this.pacienteEditando.id, this.nuevoPaciente).subscribe({
        next: (res: any) => {
          this.toast.success('Paciente actualizado');
          this.finalizarGuardado();
        },
        error: (err) => {
          console.error(err);
          this.toast.error('Error actualizando');
        },
      });
    } else {
      this.pacienteService.registrar(this.nuevoPaciente).subscribe({
        next: (res: any) => {
          this.toast.success('Paciente registrado');
          this.finalizarGuardado();
        },
        error: (err) => {
          console.error(err);
          this.toast.error('Error registrando');
        },
      });
    }
  }

  eliminar(p: Paciente): void {
    if (!p.id) return;
    if (!confirm('¿Confirmar eliminación del paciente?')) return;
    this.pacienteService.eliminar(p.id).subscribe({
      next: (res: any) => {
        this.toast.success('Paciente eliminado');
        this.obtenerPacientes();
      },
      error: (err) => {
        console.error(err);
        this.toast.error('Error eliminando');
      },
    });
  }

  toggleActivo(p: Paciente): void {
    if (!p.id) return;
    this.pacienteService.toggleActivo(p.id).subscribe({
      next: (res: any) => {
        this.toast.success('Estado actualizado');
        this.obtenerPacientes();
      },
      error: (err) => {
        console.error(err);
        this.toast.error('No se pudo actualizar estado');
      },
    });
  }
}
