import { Component, OnInit, ViewChildren, QueryList, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PortalService } from '../../../core/services/portal-service';
import { GlobalToast } from '../../../core/services/global-toast';
import 'iconify-icon';

@Component({
  selector: 'app-restablecer-password',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './restablecer-password.html',
  styleUrl: './restablecer-password.css'
})
export class RestablecerPasswordComponent implements OnInit {
  form!: FormGroup;
  email: string = '';
  errorMessage: string = '';
  exito: boolean = false;
  verPassword: boolean = false;
  verPassword2: boolean = false;

  otpDigits: string[] = ['', '', '', '', '', ''];

  @ViewChildren('otpInput') otpInputs!: QueryList<ElementRef>;

  constructor(
    private fb: FormBuilder,
    private portalService: PortalService,
    private route: ActivatedRoute,
    private router: Router,
    private toast: GlobalToast
  ) {}

  ngOnInit(): void {
    this.email = this.route.snapshot.queryParamMap.get('email') || '';
    this.form = this.fb.group({
      token: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
      nuevaPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('nuevaPassword')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  onOtpInput(event: Event, index: number): void {
    const input = event.target as HTMLInputElement;
    const value = input.value;

    if (value.length === 1 && /^\d$/.test(value)) {
      this.otpDigits[index] = value;
      this.form.get('token')?.setValue(this.otpDigits.join(''));
      if (index < 5) {
        const inputs = this.otpInputs.toArray();
        inputs[index + 1]?.nativeElement.focus();
      }
    } else {
      input.value = '';
      this.otpDigits[index] = '';
      this.form.get('token')?.setValue(this.otpDigits.join(''));
    }
  }

  onOtpKeydown(event: KeyboardEvent, index: number): void {
    if (event.key === 'Backspace') {
      if (this.otpDigits[index] === '' && index > 0) {
        const inputs = this.otpInputs.toArray();
        this.otpDigits[index - 1] = '';
        this.form.get('token')?.setValue(this.otpDigits.join(''));
        inputs[index - 1]?.nativeElement.focus();
      } else {
        this.otpDigits[index] = '';
        this.form.get('token')?.setValue(this.otpDigits.join(''));
      }
    }
  }

  onOtpPaste(event: ClipboardEvent): void {
    event.preventDefault();
    const paste = event.clipboardData?.getData('text') || '';
    const digits = paste.replace(/\D/g, '').slice(0, 6).split('');
    digits.forEach((d, i) => {
      this.otpDigits[i] = d;
    });
    this.form.get('token')?.setValue(this.otpDigits.join(''));
    const inputs = this.otpInputs.toArray();
    const focusIndex = Math.min(digits.length, 5);
    inputs[focusIndex]?.nativeElement.focus();
  }

  trackByIndex(index: number): number {
    return index;
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.errorMessage = '';
      const { token, nuevaPassword } = this.form.value;
      this.portalService.restablecerPassword(token, nuevaPassword).subscribe({
        next: () => {
          this.toast.success('Contraseña actualizada correctamente');
          this.exito = true;
          setTimeout(() => this.router.navigate(['/login']), 3000);
        },
        error: (err) => {
          this.errorMessage = err.error?.error || 'Código inválido o expirado.';
        }
      });
    }
  }
}
