import { Component, OnInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth-service';
import { PacientePortalService, PortalPerfil } from '../../../core/services/paciente-portal-service';
import { InicioPacienteComponent } from '../inicio-paciente/inicio-paciente';
import { CitasPacienteComponent } from '../citas-paciente/citas-paciente';
import { HistorialPacienteComponent } from '../historial-paciente/historial-paciente';
import { PagosPacienteComponent } from '../pagos-paciente/pagos-paciente';
import { PerfilPacienteComponent } from '../perfil-paciente/perfil-paciente';

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
  ],
  templateUrl: './portal-paciente.html',
  styleUrl: './portal-paciente.css',
})
export class PortalPacienteComponent implements OnInit {
  tabActivo = 'inicio';
  perfil: PortalPerfil | null = null;
  nombrePaciente = '';
  iniciales = '';
  pacienteId: number | null = null;
  isScrolled = false;

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 50;
  }

  bannerImagen = '';
  bannerImagenes = [
    'https://images.unsplash.com/photo-1588776814546-1ffcf47267a5?w=1600&q=80',
    'https://images.unsplash.com/photo-1631217868264-e5b90bb7e133?w=1600&q=80',
    'https://www.crp.com.pe/wp-content/uploads/2023/11/1-en-el-Top-Ranking-LATAM.jpg',
    'https://nubidoc.com/wp-content/uploads/2025/05/tipos-historias-clinicas.jpg'
  ];

  tabs = [
    { id: 'inicio', label: 'Inicio', icon: 'home' },
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

  cambiarTab(tab: string): void {
    this.tabActivo = tab;
  }

  cerrarSesion(): void {
    this.authService.logout();
  }
}
