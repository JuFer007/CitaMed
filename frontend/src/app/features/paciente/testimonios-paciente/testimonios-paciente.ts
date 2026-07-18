import { Component, OnInit, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PacientePortalService } from '../../../core/services/paciente-portal-service';
import { Testimonio } from '../../../model/Testimonio';
import { GlobalToast } from '../../../core/services/global-toast';
import 'iconify-icon';

@Component({
  selector: 'app-testimonios-paciente',
  standalone: true,
  imports: [CommonModule, FormsModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './testimonios-paciente.html',
  styleUrls: ['./testimonios-paciente.css']
})
export class TestimoniosPacienteComponent implements OnInit {

  testimonios: Testimonio[] = [];
  puedeCrear = false;
  cargando = true;

  calificacion = 0;
  calificacionHover = 0;
  mensaje = '';
  enviando = false;
  enviandoEliminar: number | null = null;

  constructor(
    private portalService: PacientePortalService,
    private toast: GlobalToast
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.cargando = true;
    this.portalService.puedeCrearTestimonio().subscribe({
      next: (res) => {
        this.puedeCrear = res.puedeCrear;
        this.cargarTestimonios();
      },
      error: () => {
        this.puedeCrear = false;
        this.cargarTestimonios();
      }
    });
  }

  cargarTestimonios(): void {
    this.portalService.obtenerMisTestimonios().subscribe({
      next: (data) => {
        this.testimonios = data;
        this.cargando = false;
      },
      error: () => {
        this.cargando = false;
      }
    });
  }

  setCalificacion(valor: number): void {
    this.calificacion = valor;
  }

  setCalificacionHover(valor: number): void {
    this.calificacionHover = valor;
  }

  getEstrellas(): number[] {
    return [1, 2, 3, 4, 5];
  }

  enviar(): void {
    if (this.calificacion === 0) {
      this.toast.warn('Selecciona una calificación');
      return;
    }
    if (!this.mensaje.trim()) {
      this.toast.warn('Escribe un mensaje');
      return;
    }

    this.enviando = true;
    this.portalService.crearTestimonio({
      calificacion: this.calificacion,
      mensaje: this.mensaje.trim()
    }).subscribe({
      next: () => {
        this.toast.success('Reseña publicada correctamente');
        this.calificacion = 0;
        this.calificacionHover = 0;
        this.mensaje = '';
        this.enviando = false;
        this.cargarTestimonios();
      },
      error: (err) => {
        this.enviando = false;
        const msg = err.error?.error || 'Error al publicar la reseña';
        this.toast.error(msg);
      }
    });
  }

  eliminar(id: number): void {
    this.enviandoEliminar = id;
    this.portalService.eliminarTestimonio(id).subscribe({
      next: () => {
        this.toast.success('Reseña eliminada');
        this.testimonios = this.testimonios.filter(t => t.id !== id);
        this.enviandoEliminar = null;
      },
      error: () => {
        this.enviandoEliminar = null;
        this.toast.error('Error al eliminar la reseña');
      }
    });
  }

  formatearFecha(fecha: string): string {
    const f = new Date(fecha);
    return f.toLocaleDateString('es-PE', { day: 'numeric', month: 'long', year: 'numeric' });
  }
}
