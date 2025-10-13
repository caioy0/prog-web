import { Routes } from '@angular/router';
import { VitrineComponent } from './vitrine/vitrine.component';
import { SobreComponent } from './sobre/sobre.component';
import { ContatoComponent } from './contato/contato.component';
import { CadastroComponent } from './cadastro/cadastro.component';

export const routes: Routes = [
    {path:"vitrine", component:VitrineComponent},
    {path:"sobre", component:SobreComponent},
    {path:"contato", component:ContatoComponent},
    { path: 'cadastro', component: CadastroComponent },
    {path:"", component:VitrineComponent}
];
