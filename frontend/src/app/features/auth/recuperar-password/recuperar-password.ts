import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PortalService } from '../../../core/services/portal-service';
import { GlobalToast } from '../../../core/services/global-toast';

@Component({
  selector: 'app-recuperar-password',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './recuperar-password.html',
  styleUrl: './recuperar-password.css'
})
export class RecuperarPasswordComponent {
  form!: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private portalService: PortalService,
    private router: Router,
    private toast: GlobalToast
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.errorMessage = '';
      this.portalService.recuperarPassword(this.form.value.email).subscribe({
        next: () => {
          this.toast.success('Código enviado a tu correo electrónico');
          const email = this.form.value.email;
          setTimeout(() => {
            this.router.navigate(['/portal/restablecer-password'], { queryParams: { email } });
          }, 1500);
        },
        error: (err) => {
          this.errorMessage = err.error?.error || 'No se pudo enviar el código. Intenta de nuevo.';
        }
      });
    }
  }
}
