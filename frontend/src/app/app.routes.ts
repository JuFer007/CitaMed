import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login-component/login-component';
import { HomeComponent } from './features/home/home-component';
import { DashboardLayout } from './layout/dashboard-layout/dashboard-layout';
import { UsuarioComponent } from './features/admin/usuario-component/usuario-component';
import { DashboardComponent } from './features/admin/dashboard-component/dashboard-component';
import { CitasComponent } from './features/admin/citas-component/citas-component';
import { MedicosComponent } from './features/admin/medicos-component/medicos-component';
import { PacientesComponent } from './features/admin/pacientes-component/pacientes-component';
import { EspecialidadesComponent } from './features/admin/especialidades-component/especialidades-component';
import { HorarioComponent } from './features/admin/horario-component/horario-component';
import { MedicamentosComponent } from './features/admin/medicamentos-component/medicamentos-component';
import { EmpleadoComponent } from './features/admin/empleado-component/empleado-component';
import { PagosComponent } from './features/admin/pagos-component/pagos-component';
import { Reportes } from './features/admin/reportes/reportes';
import { HistorialMedicoC } from './features/admin/historial-medico-c/historial-medico-c';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: 'inicio',
    component: HomeComponent,
    title: 'Citamed - Bienvenidos',
  },
  {
    path: 'login',
    component: LoginComponent,
    title: 'Iniciar Sesión',
  },
  {
    path: 'admin',
    component: DashboardLayout,
    title: 'Dashboard',
    canActivate: [authGuard],
    children: [
      { path: 'usuarios', component: UsuarioComponent, data: { title: 'Usuarios' } },
      { path: 'dashboard', component: DashboardComponent, data: { title: 'Dashboard' } },
      { path: 'citas', component: CitasComponent, data: { title: 'Citas' } },
      { path: 'medicos', component: MedicosComponent, data: { title: 'Médicos' } },
      { path: 'pacientes', component: PacientesComponent, data: { title: 'Pacientes' } },
      { path: 'especialidades', component: EspecialidadesComponent, data: { title: 'Especialidades' } },
      { path: 'horarios', component: HorarioComponent, data: { title: 'Horarios' } },
      { path: 'medicamentos', component: MedicamentosComponent, data: { title: 'Medicamentos' } },
      { path: 'empleados', component: EmpleadoComponent, data: { title: 'Empleados' } },
      { path: 'pagos', component: PagosComponent, data: { title: 'Pagos' } },
      { path: 'reportes', component: Reportes, data: { title: 'Reportes' } },
      { path: 'historial-medico', component: HistorialMedicoC, data: { title: 'Historial Médico' } }
    ]
  },
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: '**', redirectTo: 'inicio' },
];
