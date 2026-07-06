import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Table, TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { HttpClient } from '@angular/common/http';
import { SortEvent } from 'primeng/api';
import { GlobalToast } from '../../../core/services/global-toast';

interface Consulta {
  id: number;
  nombre: string;
  email: string;
  mensaje: string;
  fechaEnvio: string;
  leido: boolean;
  respondido: boolean;
  respuesta: string;
  fechaRespuesta: string;
  respondidoPor: string;
}

@Component({
  selector: 'app-consultas',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TableModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    TextareaModule,
  ],
  templateUrl: './consultas-component.html',
  styleUrl: './consultas-component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ConsultasComponent implements OnInit {
  @ViewChild('dt') dt!: Table;
  initialValue: Consulta[] = [];
  isSorted: boolean | null = null;
  private resetting = false;

  private apiUrl = 'http://localhost:8080/api/consultas';

  consultas: Consulta[] = [];
  consultasFiltradas: Consulta[] = [];
  cargando = true;
  error = '';
  terminoBusqueda = '';
  filtroEstado: string | null = null;

  mostrarDetalle = false;
  consultaSeleccionada: Consulta | null = null;

  respondiendo = false;
  respuestaTexto = '';

  autoResize(el: HTMLTextAreaElement): void {
    el.style.height = 'auto';
    el.style.height = el.scrollHeight + 'px';
  }

  constructor(
    private http: HttpClient,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.cargarConsultas();
  }

  cargarConsultas(): void {
    this.cargando = true;
    this.http.get<Consulta[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.consultas = data;
        this.aplicarFiltros();
        this.cargando = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.cargando = false;
        this.error = 'Error al cargar las consultas';
        this.cdr.markForCheck();
      },
    });
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
      this.consultasFiltradas = [...this.initialValue];
      this.dt.reset();
      setTimeout(() => { this.resetting = false; }, 0);
    }
  }

  private sortTableData(event: SortEvent): void {
    this.consultasFiltradas.sort((data1, data2) => {
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

  aplicarFiltros(): void {
    let res = [...this.consultas];

    if (this.terminoBusqueda.trim()) {
      const t = this.terminoBusqueda.toLowerCase();
      res = res.filter(
        (c) =>
          c.nombre.toLowerCase().includes(t) ||
          c.email.toLowerCase().includes(t),
      );
    }

    if (this.filtroEstado) {
      if (this.filtroEstado === 'no-leido') {
        res = res.filter((c) => !c.leido);
      } else if (this.filtroEstado === 'leido') {
        res = res.filter((c) => c.leido && !c.respondido);
      } else if (this.filtroEstado === 'respondido') {
        res = res.filter((c) => c.respondido);
      }
    }

    this.consultasFiltradas = res;
    this.initialValue = [...res];
    this.isSorted = null;
    this.cdr.markForCheck();
  }

  limpiarFiltros(): void {
    this.terminoBusqueda = '';
    this.filtroEstado = null;
    this.aplicarFiltros();
  }

  abrirDetalle(consulta: Consulta): void {
    this.consultaSeleccionada = consulta;
    this.respuestaTexto = '';
    if (!consulta.leido) {
      this.http.get(`${this.apiUrl}/${consulta.id}`).subscribe({
        next: (actualizada: any) => {
          this.consultaSeleccionada = actualizada;
          const idx = this.consultas.findIndex(c => c.id === actualizada.id);
          if (idx !== -1) this.consultas[idx] = actualizada;
          this.cdr.markForCheck();
        },
      });
    }
    this.mostrarDetalle = true;
  }

  responder(): void {
    if (!this.respuestaTexto.trim() || !this.consultaSeleccionada) return;

    const consulta = this.consultas.find(c => c.id === this.consultaSeleccionada!.id);
    if (consulta) {
      consulta.respondido = true;
      consulta.leido = true;
      consulta.respuesta = this.respuestaTexto;
    }

    const payload = {
      respuesta: this.respuestaTexto,
      respondidoPor: localStorage.getItem('nombre') || 'Admin',
    };
    const consultaId = this.consultaSeleccionada.id;

    this.toast.success('Respuesta enviada correctamente');
    this.mostrarDetalle = false;
    this.respuestaTexto = '';
    this.cdr.markForCheck();

    this.http.post(`${this.apiUrl}/${consultaId}/responder`, payload, { responseType: 'text' }).subscribe({
      error: () => {
        if (consulta) {
          consulta.respondido = false;
          consulta.leido = consulta.leido;
          this.cdr.markForCheck();
        }
        this.toast.error('Error al enviar la respuesta');
      },
    });
  }
}
