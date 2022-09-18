import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorhomeComponent } from './authorhome/authorhome.component';
import { AuthorloginComponent } from './authorlogin/authorlogin.component';
import { LoginComponent } from './login/login.component';
import { ReaderhomeComponent } from './readerhome/readerhome.component';


import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
 {path:'readerhome',component:ReaderhomeComponent},
  {path :'signup',component:SignupComponent},
  {path:'authorhome',component:AuthorhomeComponent},
 
    { path: 'authorlogin', component: AuthorloginComponent},
   
    { path: 'login', component: LoginComponent },
    { path: 'signup', component: SignupComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
