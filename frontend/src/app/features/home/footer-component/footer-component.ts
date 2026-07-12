import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface Especialidad {
  id: number;
  nombre: string;
}

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './footer-component.html',
  styleUrl: './footer-component.css'
})
export class FooterComponent implements OnInit {
  especialidades: Especialidad[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get<Especialidad[]>('http://localhost:8080/api/lading/especialidades')
      .subscribe({
        next: (data) => { this.especialidades = data; },
        error: (err) => { console.error('FooterComponent: error', err); }
      });
  }

  scrollToSection(sectionId: string): void {
    const currentUrl = this.router.url.split('?')[0].split('#')[0];
    const estaEnInicio = currentUrl === '/inicio' || currentUrl === '/';

    if (estaEnInicio) {
      this.doScroll(sectionId);
    } else {
      this.router.navigate(['/inicio']).then(() => {
        setTimeout(() => this.doScroll(sectionId), 150);
      });
    }
  }

  private doScroll(sectionId: string): void {
    const el = document.getElementById(sectionId);
    if (el) {
      const y = el.getBoundingClientRect().top + window.scrollY - 80;
      window.scrollTo({ top: y, behavior: 'smooth' });
    }
  }
}
