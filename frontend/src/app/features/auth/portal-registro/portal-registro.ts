import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PortalService } from '../../../core/services/portal-service';
import { LoaderService } from '../../../core/services/loader-service';
import { GlobalToast } from '../../../core/services/global-toast';

@Component({
  selector: 'app-portal-registro',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './portal-registro.html',
  styleUrl: './portal-registro.css',
})
export class PortalRegistroComponent implements OnInit {
  registroForm!: FormGroup;
  verPassword = false;
  reniecCargando = false;
  enviando = false;

  constructor(
    private fb: FormBuilder,
    private portalService: PortalService,
    private loaderService: LoaderService,
    private toast: GlobalToast,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registroForm = this.fb.group(
      {
        dni: ['', [Validators.required, Validators.pattern('^\\d{8}$')]],
        nombre: ['', Validators.required],
        apellidoPaterno: ['', Validators.required],
        apellidoMaterno: ['', Validators.required],
        telefono: ['', [Validators.required, Validators.pattern('^\\d{9}$')]],
        email: ['', [Validators.required, Validators.email]],
        fechaNacimiento: ['', Validators.required],
        genero: ['', Validators.required],
        direccion: [''],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmarPassword: ['', Validators.required],
      },
      { validators: this.passwordsMatch }
    );
  }

  passwordsMatch(g: FormGroup) {
    const pwd = g.get('password')?.value;
    const confirm = g.get('confirmarPassword')?.value;
    return pwd === confirm ? null : { mismatch: true };
  }

  buscarReniec(dni: string): void {
    if (!dni || dni.length !== 8) {
      this.toast.warn('Ingrese un DNI válido de 8 dígitos');
      return;
    }
    this.reniecCargando = true;
    this.portalService.consultarReniec(dni).subscribe({
      next: (res: any) => {
        this.reniecCargando = false;
        if (res?.nombres) {
          this.registroForm.patchValue({
            nombre: res.nombres || '',
            apellidoPaterno: res.apellidoPaterno || '',
            apellidoMaterno: res.apellidoMaterno || '',
          });
        }
      },
      error: () => {
        this.reniecCargando = false;
      },
    });
  }

  onSubmit(): void {
    if (this.registroForm.invalid) {
      const form = this.registroForm;
      if (form.get('dni')?.invalid) { this.toast.warn('El DNI debe tener 8 dígitos'); return; }
      if (form.get('nombre')?.invalid) { this.toast.warn('El nombre es obligatorio'); return; }
      if (form.get('apellidoPaterno')?.invalid) { this.toast.warn('El apellido paterno es obligatorio'); return; }
      if (form.get('apellidoMaterno')?.invalid) { this.toast.warn('El apellido materno es obligatorio'); return; }
      if (form.get('telefono')?.invalid) { this.toast.warn('El teléfono debe tener 9 dígitos'); return; }
      if (form.get('email')?.invalid) { this.toast.warn('Ingrese un email válido'); return; }
      if (form.get('fechaNacimiento')?.invalid) { this.toast.warn('La fecha de nacimiento es obligatoria'); return; }
      if (form.get('genero')?.invalid) { this.toast.warn('Seleccione un género'); return; }
      if (form.get('password')?.invalid) { this.toast.warn('La contraseña debe tener al menos 6 caracteres'); return; }
      if (form.get('confirmarPassword')?.invalid) { this.toast.warn('Confirme su contraseña'); return; }
      if (form.errors?.['mismatch']) { this.toast.warn('Las contraseñas no coinciden'); return; }
      return;
    }

    this.enviando = true;
    this.loaderService.show();

    const form = this.registroForm.value;
    const dto = {
      dni: form.dni,
      nombre: form.nombre,
      apellidoPaterno: form.apellidoPaterno,
      apellidoMaterno: form.apellidoMaterno,
      telefono: form.telefono,
      email: form.email,
      fechaNacimiento: form.fechaNacimiento,
      genero: form.genero,
      direccion: form.direccion,
      password: form.password,
    };

    this.portalService.registrar(dto).subscribe({
      next: (res) => {
        this.enviando = false;
        this.loaderService.hide();
        this.toast.success(res.mensaje || 'Cuenta creada exitosamente');
        this.registroForm.reset();
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: (err) => {
        this.enviando = false;
        this.loaderService.hide();
        this.toast.error(err.error?.message || err.error?.error || 'Error al registrar. Intente nuevamente.');
      },
    });
  }

  togglePassword(): void {
    this.verPassword = !this.verPassword;
  }
}
