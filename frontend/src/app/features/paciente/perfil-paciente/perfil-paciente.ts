import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PacientePortalService, PortalPerfil } from '../../../core/services/paciente-portal-service';
import { GlobalToast } from '../../../core/services/global-toast';
import { LoaderService } from '../../../core/services/loader-service';

@Component({
  selector: 'app-perfil-paciente',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './perfil-paciente.html',
  styleUrl: './perfil-paciente.css',
})
export class PerfilPacienteComponent implements OnInit {
  @Input() perfil: PortalPerfil | null = null;
  @Output() perfilActualizado = new EventEmitter<void>();

  perfilForm!: FormGroup;
  editando = false;

  constructor(
    private fb: FormBuilder,
    private portalService: PacientePortalService,
    private toast: GlobalToast,
    private loaderService: LoaderService
  ) {}

  ngOnInit(): void {
    this.perfilForm = this.fb.group({
      telefono: ['', [Validators.required, Validators.pattern('^\\d{9}$')]],
      direccion: [''],
      email: ['', [Validators.required, Validators.email]],
    });
    if (this.perfil) this.cargarForm();
  }

  cargarForm(): void {
    this.perfilForm.patchValue({
      telefono: this.perfil?.telefono || '',
      direccion: this.perfil?.direccion || '',
      email: this.perfil?.email || '',
    });
  }

  toggleEditar(): void {
    this.editando = !this.editando;
    if (this.editando) this.cargarForm();
  }

  guardar(): void {
    if (this.perfilForm.invalid) return;
    this.loaderService.show();
    this.portalService.actualizarPerfil(this.perfilForm.value).subscribe({
      next: () => {
        this.loaderService.hide();
        this.editando = false;
        this.toast.success('Perfil actualizado correctamente');
        this.perfilActualizado.emit();
      },
      error: (err) => {
        this.loaderService.hide();
        this.toast.error(err.error?.error || err.error?.message || 'Error al actualizar el perfil');
      },
    });
  }
}
