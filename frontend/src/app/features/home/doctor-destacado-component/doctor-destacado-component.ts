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
  fotoActual: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/api/lading/random')
      .subscribe({
        next: (data) => {
          this.medico = data;
          this.fotoActual = this.obtenerFotoRandom();
        },
        error: (err) => console.error(err)
      });
  }

  obtenerFotoRandom(): string {

    const mujeres = [
      'https://media.istockphoto.com/id/1468678629/es/foto/retrato-atenci%C3%B3n-m%C3%A9dica-y-tableta-con-una-mujer-m%C3%A9dico-trabajando-en-un-hospital-para.jpg?b=1&s=1024x1024&w=0&k=20&c=l53ZPXq4D00pO13ZgWLxaJserxUtlHPq5SaZwbZDrOM=',
      'https://t4.ftcdn.net/jpg/02/82/72/13/360_F_282721302_ASa8MKXhTukl1TnIxGl56eiOQkrvK5zL.jpg',
      'https://t3.ftcdn.net/jpg/03/70/84/92/360_F_370849204_JLp3LwO8tbnMEPETFggKwinyZYK7mbVv.jpg',
      'https://thumbs.dreamstime.com/b/tablero-femenino-feliz-del-doctor-stethoscope-32146067.jpg'
    ];

    const hombres = [
      'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d', 
      'https://t4.ftcdn.net/jpg/03/20/52/31/360_F_320523164_tx7Rdd7I2XDTvvKfz2oRuRpKOPE5z0ni.jpg',
      'https://www.shutterstock.com/image-photo/smiling-doctor-stethoscope-clipboard-on-600nw-2536277671.jpg', 
      'https://img.freepik.com/fotos-premium/medico-hispano-aislado-fondo-beige-alegre-despreocupado-mostrando-simbolo-paz-dedos_1187-245523.jpg?semt=ais_hybrid&w=740&q=80', 
      'https://img.freepik.com/foto-gratis/atractivo-nutriologo-masculino-joven-bata-laboratorio-sonriendo-contra-fondo-blanco_662251-2960.jpg?semt=ais_hybrid&w=740&q=80'
    ];

    const lista =
      this.medico.genero === 'FEMENINO'
        ? mujeres
        : hombres;

    const index = Math.floor(Math.random() * lista.length);

    return lista[index] + '?auto=format&fit=crop&q=80&w=1000';
  }

}
