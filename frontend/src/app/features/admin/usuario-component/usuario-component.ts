import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioService } from '../../../core/services/usuario-service';
import { LoaderService } from '../../../core/services/loader-service';
import { Usuario } from '../../../model/Perfil';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './usuario-component.html'
})
export class UsuarioComponent {

}