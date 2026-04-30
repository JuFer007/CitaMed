import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import 'iconify-icon';

@Component({
  selector: 'app-hero-component',
  standalone: true,
  imports: [CommonModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './hero-component.html'
})
export class HeroComponent {

  imagenes: string[] = [
    'https://grupooesia.com/wp-content/uploads/imagen-medica-inteligencia-artificial.jpg',
    'https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?auto=format&fit=crop&q=80&w=1200',
    'https://images.unsplash.com/photo-1584515933487-779824d29309?auto=format&fit=crop&q=80&w=1200',
    'https://www.origensalud.com/wp-content/uploads/2023/05/blog-min.png', 
    'https://plus.unsplash.com/premium_photo-1673953510197-0950d951c6d9?fm=jpg&q=60&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8bSVDMyVBOWRpY298ZW58MHx8MHx8fDA%3D',
    'https://usil-blog.s3.amazonaws.com/PROD/blog/image/especialidades-medicas-en-peru_0.jpg'
  ];

  imagenActual = '';

  constructor() {
    this.cambiarImagen();
  }

  cambiarImagen() {
    const random = Math.floor(Math.random() * this.imagenes.length);
    this.imagenActual = this.imagenes[random];
  }
}
