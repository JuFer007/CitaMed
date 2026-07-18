import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';
import { environment } from '../../../../environments/environment';
import 'iconify-icon';

@Component({
  selector: 'app-doctor-destacado',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterLink],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './doctor-destacado-component.html',
  styleUrls: ['./doctor-destacado-component.css']
})
export class DoctorDestacadoComponent implements OnInit {

  medico: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any>(`${environment.apiUrl}/api/lading/random`)
      .subscribe({
        next: (data) => {
          this.medico = data;
        },
        error: (err) => console.error(err)
      });
  }

  obtenerFotoUrl(): string {
    if (this.medico?.fotoUrl) {
      return environment.apiUrl + this.medico.fotoUrl;
    }
    return 'https://ui-avatars.com/api/?name=' + this.medico?.nombre + '+' + this.medico?.apellidoPaterno + '&background=36b2ac&color=fff&size=500';
  }

}
