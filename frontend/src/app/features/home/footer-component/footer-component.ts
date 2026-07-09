import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface Especialidad {
  id: number;
  nombre: string;
}

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './footer-component.html',
  styleUrl: './footer-component.css'
})
export class FooterComponent implements OnInit {
  especialidades: Especialidad[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    console.log('FooterComponent: ngOnInit llamado');
    this.http.get<Especialidad[]>('http://localhost:8080/api/lading/especialidades')
      .subscribe({
        next: (data) => { console.log('FooterComponent: datos recibidos', data); this.especialidades = data; },
        error: (err) => { console.error('FooterComponent: error', err); }
      });
  }
}
