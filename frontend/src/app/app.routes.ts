import { Routes } from '@angular/router';
import { NotFoundComponent } from './pages/not-found/not-found.component';

export const routes: Routes = [
    {
      path: 'auth',
      loadChildren: () => import('./pages/authentication/authentication.module').then(m => m.AuthenticationModule)
    },

    { path: '', redirectTo: '/auth', pathMatch: 'full' },
    { path: 'notfound', component: NotFoundComponent },
    { path: '**', redirectTo: '/notfound' }
  ];