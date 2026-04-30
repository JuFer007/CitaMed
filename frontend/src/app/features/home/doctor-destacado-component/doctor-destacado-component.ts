import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import 'iconify-icon';

@Component({
  selector: 'app-doctor-destacado',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './doctor-destacado-component.html',
  styleUrls: ['./doctor-destacado-component.css']
})
export class DoctorDestacadoComponent implements OnInit {

  medico: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/api/lading/random')
      .subscribe({
        next: (data) => this.medico = data,
        error: (err) => console.error(err)
      });
  }

  obtenerFoto(): string {

    if (!this.medico) return '';

    if (this.medico.genero === 'FEMENINO') {
      return 'https://media.istockphoto.com/id/1806608544/es/foto/retrato-de-una-doctora-en-el-lugar-de-trabajo.jpg?b=1&s=1024x1024&w=0&k=20&c=b57xIjpIkjIkFOxr6uEVJXoX_vjfVJUYbiuR0vPeoGw=';
    }

    return 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?auto=format&fit=crop&q=80&w=1000';
  }
}
