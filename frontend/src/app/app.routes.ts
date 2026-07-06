import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login-component/login-component';
import { HomeComponent } from './features/home/home-component';
import { DashboardLayout } from './layout/dashboard-layout/dashboard-layout';
import { UsuarioComponent } from './features/admin/usuario-component/usuario-component';
import { DashboardComponent } from './features/admin/dashboard-component/dashboard-component';
import { CitasComponent } from './features/admin/citas-component/citas-component';
import { MedicosComponent } from './features/admin/medicos-component/medicos-component';
import { ConsultoriosComponent } from './features/admin/consultorios-component/consultorios-component';
import { PacientesComponent } from './features/admin/pacientes-component/pacientes-component';
import { EspecialidadesComponent } from './features/admin/especialidades-component/especialidades-component';
import { HorarioComponent } from './features/admin/horario-component/horario-component';
import { EmpleadoComponent } from './features/admin/empleado-component/empleado-component';
import { PagosComponent } from './features/admin/pagos-component/pagos-component';
import { ConsultasComponent } from './features/admin/consultas-component/consultas-component';
import { Reportes } from './features/admin/reportes/reportes';
import { DiagnosticosComponent } from './features/admin/diagnosticos/diagnosticos-component';
import { HistorialMedicoC } from './features/admin/historial-medico-c/historial-medico-c';
import { authGuard } from './core/guards/auth.guard';
import { Page404Component } from './shared/components/page404-component/page404-component';
import { DoctoresPage } from './features/home/doctores-page/doctores-page';
import { roleGuard } from './core/guards/role.guard';

export const routes: Routes = [
  {
    path: 'inicio',
    component: HomeComponent,
    title: 'CitaMed - Home',
  },
  {
    path: 'login',
    component: LoginComponent,
    title: 'Iniciar Sesión',
  },
  {
    path: 'staff-medico',
    component: DoctoresPage,
    title: 'Staff Medico CitaMed',
  },
  {
    path: 'admin',
    component: DashboardLayout,
    title: 'CitaMed - Dashboard',
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'usuarios',
        component: UsuarioComponent,
        data: { title: 'Usuarios', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      { path: 'dashboard', component: DashboardComponent, data: { title: 'Dashboard' } },
      {
        path: 'citas',
        component: CitasComponent,
        data: { title: 'Citas', roles: ['ADMIN', 'MEDICO', 'RECEPCIONISTA'] },
        canActivate: [roleGuard],
      },
      {
        path: 'diagnosticos',
        component: DiagnosticosComponent,
        data: { title: 'Diagnósticos', roles: ['ADMIN', 'MEDICO'] },
        canActivate: [roleGuard],
      },
      {
        path: 'medicos',
        component: MedicosComponent,
        data: { title: 'Médicos', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'consultorios',
        component: ConsultoriosComponent,
        data: { title: 'Consultorios', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'pacientes',
        component: PacientesComponent,
        data: { title: 'Pacientes', roles: ['ADMIN', 'MEDICO', 'RECEPCIONISTA'] },
        canActivate: [roleGuard],
      },
      {
        path: 'especialidades',
        component: EspecialidadesComponent,
        data: { title: 'Especialidades', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'horarios',
        component: HorarioComponent,
        data: { title: 'Horarios', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'empleados',
        component: EmpleadoComponent,
        data: { title: 'Empleados', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'pagos',
        component: PagosComponent,
        data: { title: 'Pagos', roles: ['ADMIN', 'RECEPCIONISTA'] },
        canActivate: [roleGuard],
      },
      {
        path: 'consultas',
        component: ConsultasComponent,
        data: { title: 'Consultas', roles: ['ADMIN', 'MEDICO', 'RECEPCIONISTA'] },
        canActivate: [roleGuard],
      },
      {
        path: 'reportes',
        component: Reportes,
        data: { title: 'Reportes', roles: ['ADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'historial-medico',
        component: HistorialMedicoC,
        data: { title: 'Historial Médico', roles: ['ADMIN', 'MEDICO', 'RECEPCIONISTA'] },
        canActivate: [roleGuard],
      },
    ],
  },
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: '**', component: Page404Component },
];
