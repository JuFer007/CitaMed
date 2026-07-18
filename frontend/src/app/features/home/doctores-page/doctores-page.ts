import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Subject, forkJoin, debounceTime, distinctUntilChanged, takeUntil } from 'rxjs';
import { NavbarComponent } from '../navbar-component/navbar-component';
import { FooterComponent } from '../footer-component/footer-component';
import { Medico } from '../../../model/Medico';
import { Especialidad } from '../../../model/Especialidad';
import { environment } from '../../../../environments/environment';
import 'iconify-icon';

@Component({
  selector: 'app-doctores-page',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    NavbarComponent,
    FooterComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './doctores-page.html',
  styleUrls: ['./doctores-page.css']
})
export class DoctoresPage implements OnInit, OnDestroy {

  private baseUrl = `${environment.apiUrl}/api/lading`;
  private destroy$ = new Subject<void>();
  private search$ = new Subject<string>();

  medicos: Medico[] = [];
  medicosFiltrados: Medico[] = [];
  especialidades: Especialidad[] = [];

  filtroNombre = '';
  especialidadSeleccionada: number | null = null;
  cargando = true;

  readonly fallbackSvg = 'data:image/svg+xml,' + encodeURIComponent(
    '<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 64 64"><path fill="%2336b2ac" d="M32.9 33.62a5.9 5.9 0 0 1-6.31-5.78.5.5 0 0 1 .5-.51L39 27.17a.45.45 0 0 1 .38.17.49.49 0 0 1 .13.4 6.56 6.56 0 0 1-6.26 5.88Zm-5.25-5.28a5.15 5.15 0 0 0 1.45 2.91 5.36 5.36 0 0 0 4.07 1.35 5.45 5.45 0 0 0 5.18-4.41Zm5.22-3.19a6.4 6.4 0 0 1-2.21-.88 1.76 1.76 0 0 1-.83-1.08 1.86 1.86 0 0 1 .37-1.38 20 20 0 0 0 1.37-4.15.52.52 0 0 1 .62-.38.51.51 0 0 1 .37.62 21.7 21.7 0 0 1-1.51 4.47 1 1 0 0 0-.22.62c0 .14.15.27.4.43a6.5 6.5 0 0 0 1.65.72.52.52 0 0 1 .48.52.49.49 0 0 1-.49.49m0 32.2c-.69 0-1.36-.45-2.11-1.42-1.87-2.42-4.32-7.44-5.64-10.13l-.05-.09a.51.51 0 0 1 .23-.68.5.5 0 0 1 .68.23l.05.09c1.3 2.66 3.73 7.62 5.54 10 .51.67 1 1 1.3 1 .45 0 .93-.65 1.44-1.34l.33-.43 5.1-8.82a.51.51 0 0 1 .88.51l-5.14 8.87-.35.48c-.67.89-1.3 1.74-2.25 1.75ZM63.6 63c-4.69-1.07-6.18-8.16-6.2-8.25-2.23-9.76-9.51-10.5-13-10.85l-.76-.08c-2.16-.26-3.19-2.8-3.44-4.11a.51.51 0 0 0-1 .19c.3 1.56 1.57 4.59 4.32 4.93l.77.08a26 26 0 0 1 2.85.4c1.76 2.2 1.68 5.48 1 7.49a3.4 3.4 0 0 0-2.2-.8 3.33 3.33 0 1 0 3.37 3.33 3.3 3.3 0 0 0-.41-1.56 10 10 0 0 0-.29-8c3.11 1 6.43 3.3 7.8 9.28.06.32 1.63 7.81 7 9h.11a.52.52 0 0 0 .5-.4.5.5 0 0 0-.42-.65m-17.66-5.4a2.31 2.31 0 1 1 2.36-2.31 2.34 2.34 0 0 1-2.36 2.31M26.76 39.73a.51.51 0 0 0-.69.22l-.15.34c-.39 1-1.2 3-6.46 3.91-6.78 1.23-9.86 4.57-13.19 14.28 0 .14-1.16 3.45-5.87 4.52a.5.5 0 0 0-.39.6.52.52 0 0 0 .5.4h.11c5.3-1.2 6.57-5 6.62-5.19 3.34-9.74 6.32-12.27 11.42-13.4a8.3 8.3 0 0 0 .3 3c-1.68.52-3.17 3.1-4.14 5.25a10.46 10.46 0 0 0 .32 8.55.52.52 0 0 0 .45.27h1.53a.51.51 0 1 0 0-1h-1.21a9.3 9.3 0 0 1-.16-7.38c1.28-2.83 2.66-4.6 3.69-4.74a1 1 0 0 1 .95.49 13.45 13.45 0 0 1 2.73 11.63h-1.73a.51.51 0 0 0 0 1h2.14a.52.52 0 0 0 .5-.39 14.54 14.54 0 0 0-2.85-12.91 2.3 2.3 0 0 0-1.15-.8v-.06a6.17 6.17 0 0 1-.25-3.16c5.68-1.07 6.67-3.47 7.08-4.5.05-.1.08-.19.12-.26a.5.5 0 0 0-.22-.67m20.1-18.93a2.14 2.14 0 0 0-2.38 1 .51.51 0 0 0 .13.71.5.5 0 0 0 .7-.13c.37-.53.83-.76 1.25-.62s1 .75 1 2a2.19 2.19 0 0 1-1.46 2 .62.62 0 0 1-.67-.12v-.04a.5.5 0 0 0-.5-.51.51.51 0 0 0-.51.51c0 .9-.16 5.56-3.34 8.7-2 2-4.9 3-8.49 2.93a10 10 0 0 1-7.37-2.86c-3.65-3.78-3.94-10.63-3.84-15.46a.51.51 0 0 0-.5-.52.49.49 0 0 0-.52.5v2.08a1.8 1.8 0 0 0-1.46-.17c-1 .33-1.68 1.47-1.68 3a3.33 3.33 0 0 0 2.38 3.05 2.1 2.1 0 0 0 .64.1 1.6 1.6 0 0 0 .6-.12 15.5 15.5 0 0 0 3.66 8.26 11 11 0 0 0 8.08 3.17h.29c3.75 0 6.76-1.09 8.93-3.23a13.3 13.3 0 0 0 3.58-8.19 1.6 1.6 0 0 0 .45.06 1.9 1.9 0 0 0 .56-.08 3.2 3.2 0 0 0 2.19-3c-.04-1.55-.68-2.69-1.72-3.02m-27 5.07a2.35 2.35 0 0 1-1.64-2.08c0-1.27.52-1.88 1-2a1.11 1.11 0 0 1 1.2.56c0 1.11.13 2.25.28 3.4a.79.79 0 0 1-.86.12Zm.75-8.36A.5.5 0 0 0 21 17c-.1-1 .14-3.2 1.81-4.12 1.47-.81 3.31-.51 5.08-.22a17.5 17.5 0 0 0 2.59.28c2.24 0 7.78-1.1 10.38-2.67a7.1 7.1 0 0 0 2 3.24 5.23 5.23 0 0 1 2.2 3.22.51.51 0 0 0 .38.49.49.49 0 0 0 .57-.26 6.8 6.8 0 0 0 .75-2.54.51.51 0 1 0-1 0 2.8 2.8 0 0 1-.12.76 7.3 7.3 0 0 0-2.17-2.5 6.83 6.83 0 0 1-1.87-3.34.5.5 0 0 0-.83-.34c-2 1.56-8.06 2.86-10.34 2.86a16 16 0 0 1-2.43-.24c-1.85-.3-3.95-.64-5.73.34a4.3 4.3 0 0 0-1.95 2.35A10.9 10.9 0 0 1 21.26 9c1.52-3.44 5.08-7.59 13.32-8 5.42-.22 7.66 2.28 8.09 3.64A.54.54 0 0 0 43 5c3.26 1.44 3.71 5 3.49 6.73a.51.51 0 0 0 .43.57.51.51 0 0 0 .57-.44c.25-1.92-.25-6-3.88-7.7-.67-1.64-3.26-4.36-9-4.09-6.91.26-11.96 3.3-14.28 8.5a11.1 11.1 0 0 0-.33 8.67.52.52 0 0 0 .46.29.4.4 0 0 0 .13-.02Z"/></svg>'
  );

  private fotosMujeres = [
    'https://media.istockphoto.com/id/1468678629/es/foto/retrato-atenci%C3%B3n-m%C3%A9dica-y-tableta-con-una-mujer-m%C3%A9dico-trabajando-en-un-hospital-para.jpg?b=1&s=1024x1024&w=0&k=20&c=l53ZPXq4D00pO13ZgWLxaJserxUtlHPq5SaZwbZDrOM=',
    'https://t4.ftcdn.net/jpg/02/82/72/13/360_F_282721302_ASa8MKXhTukl1TnIxGl56eiOQkrvK5zL.jpg',
    'https://t3.ftcdn.net/jpg/03/70/84/92/360_F_370849204_JLp3LwO8tbnMEPETFggKwinyZYK7mbVv.jpg',
    'https://thumbs.dreamstime.com/b/tablero-femenino-feliz-del-doctor-stethoscope-32146067.jpg'
  ];

  private fotosHombres = [
    'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d',
    'https://t4.ftcdn.net/jpg/03/20/52/31/360_F_320523164_tx7Rdd7I2XDTvvKfz2oRuRpKOPE5z0ni.jpg',
    'https://www.shutterstock.com/image-photo/smiling-doctor-stethoscope-clipboard-on-600nw-2536277671.jpg',
    'https://img.freepik.com/fotos-premium/medico-hispano-aislado-fondo-beige-alegre-despreocupado-mostrando-simbolo-paz-dedos_1187-245523.jpg?semt=ais_hybrid&w=740&q=80',
    'https://img.freepik.com/foto-gratis/atractivo-nutriologo-masculino-joven-bata-laboratorio-sonriendo-contra-fondo-blanco_662251-2960.jpg?semt=ais_hybrid&w=740&q=80'
  ];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    forkJoin({
      medicos: this.http.get<Medico[]>(`${this.baseUrl}/medicos`),
      especialidades: this.http.get<Especialidad[]>(`${this.baseUrl}/especialidades`)
    })
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: ({ medicos, especialidades }) => {
          this.medicos = medicos;
          this.medicosFiltrados = [...medicos];
          this.especialidades = especialidades;
          this.cargando = false;
        },
        error: (err) => { console.error(err); this.cargando = false; }
      });

    this.search$
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        takeUntil(this.destroy$)
      )
      .subscribe(valor => {
        this.filtroNombre = valor;
        this.aplicarFiltros();
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onSearchInput(valor: string): void {
    this.search$.next(valor);
  }

  aplicarFiltros(): void {
    let resultado = [...this.medicos];

    if (this.filtroNombre.trim()) {
      const busqueda = this.filtroNombre.toLowerCase().trim();
      resultado = resultado.filter(m =>
        m.nombre.toLowerCase().includes(busqueda) ||
        m.apellidoPaterno.toLowerCase().includes(busqueda) ||
        m.apellidoMaterno.toLowerCase().includes(busqueda)
      );
    }

    if (this.especialidadSeleccionada !== null) {
      resultado = resultado.filter(m =>
        m.especialidad && m.especialidad.id === this.especialidadSeleccionada
      );
    }

    this.medicosFiltrados = resultado;
  }

  limpiarFiltros(): void {
    this.filtroNombre = '';
    this.especialidadSeleccionada = null;
    this.medicosFiltrados = [...this.medicos];
  }

  obtenerFoto(medico: Medico): string {
    if (medico.fotoUrl) {
      return `${environment.apiUrl}${medico.fotoUrl}`;
    }
    const lista = medico.genero === 'FEMENINO' ? this.fotosMujeres : this.fotosHombres;
    const index = medico.id ? medico.id % lista.length : 0;
    const url = lista[index];
    return url + (url.includes('?') ? '&' : '?') + 'auto=format&fit=crop&q=80&w=1000';
  }

  onImageError(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = this.fallbackSvg;
  }
}
