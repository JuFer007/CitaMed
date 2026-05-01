import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth-service';

@Component({
  selector: 'app-navbar-component',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar-component.html'
})
export class NavbarComponent implements OnInit {
  isScrolled = false;
  isLoggedIn = false;
  nombre = '';
  inicial = '';
  menuPerfil = false;
  menuMobile = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    if (this.isLoggedIn) {
      this.nombre = localStorage.getItem('nombre') ?? '';
      this.inicial = this.nombre.charAt(0).toUpperCase();
    }
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 50;
  }

  /**
   * Navega a la página de inicio y luego hace scroll a la sección indicada.
   * Si ya estamos en /inicio, hace scroll directo sin navegar.
   */
  scrollToSection(sectionId: string): void {
    this.menuMobile = false;
    this.menuPerfil = false;

    const currentUrl = this.router.url.split('?')[0].split('#')[0];
    const estaEnInicio = currentUrl === '/inicio' || currentUrl === '/';

    if (estaEnInicio) {
      this.doScroll(sectionId);
    } else {
      this.router.navigate(['/inicio']).then(() => {
        // Pequeño delay para que Angular renderice la página antes de hacer scroll
        setTimeout(() => this.doScroll(sectionId), 150);
      });
    }
  }

  private doScroll(sectionId: string): void {
    const el = document.getElementById(sectionId);
    if (el) {
      // Offset de 80px para compensar el navbar fijo
      const y = el.getBoundingClientRect().top + window.scrollY - 80;
      window.scrollTo({ top: y, behavior: 'smooth' });
    }
  }

  toggleMenuPerfil(): void {
    this.menuPerfil = !this.menuPerfil;
  }

  toggleMenuMobile(): void {
    this.menuMobile = !this.menuMobile;
  }

  cerrarSesion(): void {
    this.authService.logout();
    this.isLoggedIn = false;
    this.menuPerfil = false;
    this.menuMobile = false;
  }
}
