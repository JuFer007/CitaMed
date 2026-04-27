import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login-component/login-component';
import { HomeComponent } from './features/home/home-component';
import { DashboardLayout } from './layout/dashboard-layout/dashboard-layout';
import { UsuarioComponent } from './features/admin/usuario-component/usuario-component';
import { DashboardComponent } from './features/admin/dashboard-component/dashboard-component';
import { VentasComponent } from './features/admin/ventas-component/ventas-component';
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
      { path: 'ventas', component: VentasComponent, data: { title: 'Ventas' } }
    ]
  },
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: '**', redirectTo: 'inicio' },
];
