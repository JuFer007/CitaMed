import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';
import { loadStripe, Stripe } from '@stripe/stripe-js';
import { PacientePortalService, PortalCita, PortalDiagnostico } from '../../../core/services/paciente-portal-service';
import { GlobalToast } from '../../../core/services/global-toast';
import { STRIPE_PUBLISHABLE_KEY } from '../../../core/services/stripe-config';

@Component({
  selector: 'app-citas-paciente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './citas-paciente.html',
  styleUrl: './citas-paciente.css',
})
export class CitasPacienteComponent implements OnInit, OnDestroy {
  proximas: PortalCita[] = [];
  historial: PortalCita[] = [];
  vista = 'proximas';
  modalAbierto = false;
  citaModal: PortalCita | null = null;
  diagnosticoModal: PortalDiagnostico | null = null;
  modalDiagnosticoAbierto = false;
  citaDiagnostico: PortalCita | null = null;
  cargandoDiagnostico = false;
  cargandoPdf = false;
  sinDiagnostico = false;
  private recargaSub?: Subscription;

  // Stripe payment modal
  modalPagoAbierto = false;
  citaPago: PortalCita | null = null;
  private stripe: Stripe | null = null;
  private cardElement: any = null;
  pagoClientSecret = '';
  procesandoPago = false;
  errorPago = '';

  constructor(
    private portalService: PacientePortalService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarCitas();
    this.recargaSub = this.portalService.recargarCitas$.subscribe(() => this.cargarCitas());
  }

  ngOnDestroy(): void {
    this.recargaSub?.unsubscribe();
    if (this.cardElement) { this.cardElement.destroy(); }
  }

  private cargarCitas(): void {
    this.portalService.obtenerProximasCitas().subscribe({
      next: (data) => { this.proximas = data; this.cdr.markForCheck(); },
    });
    this.portalService.obtenerHistorialCitas().subscribe({
      next: (data) => { this.historial = data; this.cdr.markForCheck(); },
    });
  }

  verDetalle(cita: PortalCita): void {
    this.citaModal = cita;
    this.modalAbierto = true;
  }

  verDiagnostico(cita: PortalCita): void {
    this.citaDiagnostico = cita;
    this.diagnosticoModal = null;
    this.sinDiagnostico = false;
    this.modalDiagnosticoAbierto = true;

    const diag = cita.diagnostico;
    if (diag) {
      this.diagnosticoModal = diag;
      return;
    }

    this.portalService.obtenerDiagnostico(cita.id).subscribe({
      next: (data) => {
        this.diagnosticoModal = data;
      },
      error: () => {
        this.sinDiagnostico = true;
      },
    });
  }

  descargarDiagnostico(): void {
    if (!this.citaDiagnostico) return;
    this.cargandoPdf = true;
    this.toast.info('Generando diagnóstico PDF...', { life: 999999 });
    this.portalService.descargarRecetaPdf(this.citaDiagnostico.id).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        window.open(url, '_blank');
        this.cargandoPdf = false;
        this.toast.clear();
        this.toast.success('Diagnóstico descargado');
      },
      error: () => {
        this.cargandoPdf = false;
        this.toast.clear();
        this.toast.error('Error al generar el diagnóstico PDF');
      },
    });
  }

  pagarAhora(cita: PortalCita): void {
    this.citaPago = cita;
    this.modalPagoAbierto = true;
    this.errorPago = '';
    this.procesandoPago = false;
    this.cdr.detectChanges();
    this.portalService.crearPagoIntent(cita.id).subscribe({
      next: async (res) => {
        this.pagoClientSecret = res.clientSecret;
        this.cdr.detectChanges();
        await this.iniciarStripePago();
      },
      error: () => {
        this.toast.error('Error al iniciar el pago');
        this.cerrarModalPago();
      },
    });
  }

  private async iniciarStripePago(): Promise<void> {
    this.stripe = await loadStripe(STRIPE_PUBLISHABLE_KEY);
    if (!this.stripe) return;
    const elements = this.stripe.elements();
    this.cardElement = elements.create('card', {
      style: {
        base: {
          fontSize: '16px',
          color: '#083d3a',
          fontFamily: 'Inter, system-ui, sans-serif',
          '::placeholder': { color: '#9ca3af' },
        },
        invalid: { color: '#dc2626' },
      },
    });
    setTimeout(() => {
      const container = document.getElementById('card-element-pago');
      if (container) this.cardElement.mount(container);
    }, 50);
  }

  async confirmarPagoStripe(): Promise<void> {
    if (!this.stripe || !this.cardElement || !this.citaPago) return;
    this.procesandoPago = true;
    this.errorPago = '';

    const { error, paymentIntent } = await this.stripe.confirmCardPayment(this.pagoClientSecret, {
      payment_method: { card: this.cardElement },
    });

    if (error) {
      this.errorPago = error.message || 'Error al procesar el pago';
      this.procesandoPago = false;
      this.cdr.detectChanges();
      return;
    }

    if (paymentIntent?.status === 'succeeded') {
      this.portalService.confirmarPago(this.citaPago.id, paymentIntent.id).subscribe({
        next: () => {
          this.toast.success('Pago realizado exitosamente');
          this.cerrarModalPago();
          this.cargarCitas();
        },
        error: () => {
          this.toast.error('Error al confirmar el pago');
          this.cerrarModalPago();
        },
      });
    } else {
      this.errorPago = 'El pago no se completó. Intenta nuevamente.';
      this.procesandoPago = false;
      this.cdr.detectChanges();
    }
  }

  cerrarModalPago(): void {
    this.modalPagoAbierto = false;
    this.citaPago = null;
    this.pagoClientSecret = '';
    this.errorPago = '';
    this.procesandoPago = false;
    if (this.cardElement) { this.cardElement.destroy(); this.cardElement = null; }
    this.stripe = null;
  }

  cancelarCita(id: number): void {
    if (!confirm('¿Estás seguro de cancelar esta cita?')) return;
    this.portalService.cancelarCita(id).subscribe({
      next: () => {
        this.toast.success('Cita cancelada correctamente');
        this.cerrarModal();
        this.proximas = this.proximas.filter(c => c.id !== id);
      },
      error: () => this.toast.error('Error al cancelar la cita'),
    });
  }

  cerrarModal(): void {
    this.modalAbierto = false;
    this.citaModal = null;
  }

  cerrarModalDiagnostico(): void {
    this.modalDiagnosticoAbierto = false;
    this.citaDiagnostico = null;
    this.diagnosticoModal = null;
    this.sinDiagnostico = false;
  }

  estadoClase(estado: string): string {
    const map: Record<string, string> = {
      PROGRAMADA: 'bg-[#36b1ae]/10 text-[#0f8b86]',
      CONFIRMADA: 'bg-[#36b1ae]/10 text-[#0f8b86]',
      ATENDIDA: 'bg-[#eaf3de] text-[#3b6d11]',
      CANCELADA: 'bg-slate-100 text-slate-500',
      NO_ASISTIO: 'bg-[#fcebeb] text-[#a32d2d]',
    };
    return map[estado] || 'bg-slate-100 text-slate-500';
  }

  formatearFecha(fecha: string): string {
    if (!fecha) return '';
    const d = new Date(fecha);
    return d.toLocaleDateString('es-PE', { day: 'numeric', month: 'short', year: 'numeric' });
  }
}
