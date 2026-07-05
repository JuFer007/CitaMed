import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-consultas',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './consultas-component.html',
  styleUrl: './consultas-component.css'
})
export class ConsultasComponent {
  private apiUrl = 'http://localhost:8080/api/consultas';

  form = { nombre: '', email: '', mensaje: '' };
  enviando = false;
  exito = false;
  error = '';

  constructor(private http: HttpClient) {}

  enviar(): void {
    if (!this.form.nombre.trim() || !this.form.email.trim() || !this.form.mensaje.trim()) return;

    const payload = { ...this.form };

    this.form = { nombre: '', email: '', mensaje: '' };
    this.exito = true;
    this.error = '';

    this.http.post(this.apiUrl, payload, { responseType: 'text' }).subscribe({
      error: () => {
        this.exito = false;
        this.error = 'Error al enviar tu consulta. Intenta nuevamente.';
      }
    });
  }
}
