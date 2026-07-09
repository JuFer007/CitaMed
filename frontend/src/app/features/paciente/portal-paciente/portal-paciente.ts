import { Component, OnInit, OnDestroy, HostListener, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth-service';
import { PacientePortalService, PortalPerfil, PortalNotificacion } from '../../../core/services/paciente-portal-service';
import { InicioPacienteComponent } from '../inicio-paciente/inicio-paciente';
import { CitasPacienteComponent } from '../citas-paciente/citas-paciente';
import { HistorialPacienteComponent } from '../historial-paciente/historial-paciente';
import { PagosPacienteComponent } from '../pagos-paciente/pagos-paciente';
import { PerfilPacienteComponent } from '../perfil-paciente/perfil-paciente';
import { ReservarCitaPacienteComponent } from '../reservar-cita-paciente/reservar-cita-paciente';
import { FooterComponent } from '../../home/footer-component/footer-component';
import { interval, Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-portal-paciente',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    InicioPacienteComponent,
    CitasPacienteComponent,
    HistorialPacienteComponent,
    PagosPacienteComponent,
    PerfilPacienteComponent,
    ReservarCitaPacienteComponent,
    FooterComponent,
  ],
  templateUrl: './portal-paciente.html',
  styleUrl: './portal-paciente.css',
})
export class PortalPacienteComponent implements OnInit, OnDestroy {
  @ViewChild('notificacionPanel') notificacionPanel!: ElementRef;

  tabActivo = 'inicio';
  perfil: PortalPerfil | null = null;
  nombrePaciente = '';
  iniciales = '';
  pacienteId: number | null = null;
  isScrolled = false;

  notificaciones: PortalNotificacion[] = [];
  noLeidas = 0;
  dropdownAbierto = false;
  private pollingSub?: Subscription;
  private notifSub?: Subscription;

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 50;
  }

  @HostListener('document:click', ['$event'])
  onClickFuera(event: Event) {
    if (this.dropdownAbierto && this.notificacionPanel && !this.notificacionPanel.nativeElement.contains(event.target)) {
      this.dropdownAbierto = false;
    }
  }

  bannerImagen = '';
  bannerImagenes = [
    'https://images.unsplash.com/photo-1579684385127-1ef15d508118?w=1600&q=80',
    'https://images.unsplash.com/photo-1588776814546-1ffcf47267a5?w=1600&q=80',
    'https://www.crp.com.pe/wp-content/uploads/2023/11/1-en-el-Top-Ranking-LATAM.jpg',
    'https://images.unsplash.com/photo-1581056771107-24ca5f033842?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
    'https://images.unsplash.com/photo-1758691461990-03b49d969495?q=80&w=1332&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
    'https://images.unsplash.com/photo-1758691462878-6edc3d3da1be?q=80&w=1332&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
  ];

  tabs = [
    { id: 'inicio', label: 'Inicio', icon: 'home' },
    { id: 'reservar', label: 'Reservar cita', icon: 'add_circle' },
    { id: 'citas', label: 'Mis citas', icon: 'calendar_month' },
    { id: 'historial', label: 'Historial clínico', icon: 'history' },
    { id: 'pagos', label: 'Pagos', icon: 'payments' },
    { id: 'perfil', label: 'Mi perfil', icon: 'person' },
  ];

  constructor(
    private authService: AuthService,
    private portalService: PacientePortalService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bannerImagen = this.bannerImagenes[Math.floor(Math.random() * this.bannerImagenes.length)];
    this.pacienteId = this.obtenerPacienteId();
    if (!this.pacienteId) {
      this.router.navigate(['/login']);
      return;
    }
    this.cargarPerfil();
    this.cargarNotificaciones();
    this.pollingSub = interval(30000).subscribe(() => {
      this.cargarNotificaciones();
    });
  }

  ngOnDestroy(): void {
    this.pollingSub?.unsubscribe();
    this.notifSub?.unsubscribe();
  }

  obtenerPacienteId(): number | null {
    const id = localStorage.getItem('pacienteId');
    return id ? Number(id) : null;
  }

  cargarPerfil(): void {
    this.portalService.obtenerPerfil().subscribe({
      next: (data) => {
        this.perfil = data;
        this.nombrePaciente = `${data.nombre} ${data.apellidoPaterno} ${data.apellidoMaterno}`;
        this.iniciales = (data.nombre.charAt(0) + data.apellidoPaterno.charAt(0)).toUpperCase();
      },
    });
  }

  toggleNotificaciones(): void {
    this.dropdownAbierto = !this.dropdownAbierto;
  }

  cargarNotificaciones(): void {
    this.notifSub?.unsubscribe();
    this.notifSub = this.portalService.obtenerNotificaciones().subscribe({
      next: (data) => {
        this.notificaciones = data;
        this.noLeidas = data.filter(n => !n.leido).length;
      },
      error: (err) => {
        console.error('Error al cargar notificaciones', err);
      },
    });
  }

  marcarLeida(notif: PortalNotificacion, event: Event): void {
    event.stopPropagation();
    if (notif.leido) return;
    this.portalService.marcarNotificacionLeida(notif.id).subscribe({
      next: () => {
        notif.leido = true;
        this.noLeidas = Math.max(0, this.noLeidas - 1);
      },
    });
  }

  marcarTodasLeidas(event: Event): void {
    event.stopPropagation();
    this.portalService.marcarTodasNotificacionesLeidas().subscribe({
      next: () => {
        this.notificaciones.forEach(n => n.leido = true);
        this.noLeidas = 0;
      },
    });
  }

  irANotificacion(notif: PortalNotificacion, event: Event): void {
    event.stopPropagation();
    if (!notif.leido) {
      this.portalService.marcarNotificacionLeida(notif.id).subscribe({
        next: () => {
          notif.leido = true;
          this.noLeidas = Math.max(0, this.noLeidas - 1);
        },
      });
    }
    this.dropdownAbierto = false;
    if (notif.tabDestino) {
      this.tabActivo = notif.tabDestino;
    }
  }

  tiempoRelativo(fecha: string): string {
    const ahora = new Date();
    const f = new Date(fecha);
    const diffSeg = Math.floor((ahora.getTime() - f.getTime()) / 1000);

    if (diffSeg < 60) return 'ahora';
    const diffMin = Math.floor(diffSeg / 60);
    if (diffMin < 60) return `hace ${diffMin}m`;
    const diffHoras = Math.floor(diffMin / 60);
    if (diffHoras < 24) return `hace ${diffHoras}h`;
    const diffDias = Math.floor(diffHoras / 24);
    if (diffDias === 1) return 'ayer';
    if (diffDias < 7) return `hace ${diffDias}d`;
    return f.toLocaleDateString('es-PE', { day: 'numeric', month: 'short' });
  }

  cambiarTab(tab: string): void {
    this.tabActivo = tab;
  }

  cerrarSesion(): void {
    this.authService.logout();
  }
}
