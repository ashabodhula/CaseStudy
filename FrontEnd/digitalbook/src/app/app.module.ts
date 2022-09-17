import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { SignupComponent } from './signup/signup.component';

import { LoginComponent } from './login/login.component';
import { ReaderhomeComponent } from './readerhome/readerhome.component';
import { GetbooksComponent } from './getbooks/getbooks.component';
import { AuthorloginComponent } from './authorlogin/authorlogin.component';
import { AuthorhomeComponent } from './authorhome/authorhome.component';
import { CreatebookComponent } from './createbook/createbook.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthorsignupComponent } from './authorsignup/authorsignup.component';

import { UpdatebookComponent } from './updatebook/updatebook.component';




const routes: Routes = [
  { path: 'authorhome', component: AuthorhomeComponent },
  { path: 'authorlogin', component: AuthorloginComponent},
  { path: 'readerhome', component: ReaderhomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent},
{path:'authorsignup',component:AuthorsignupComponent},
{path:'createbook',component:CreatebookComponent},

{path:'getbooks',component:GetbooksComponent}
 

]

@NgModule({
  declarations: [
    AppComponent,

    SignupComponent,

    LoginComponent,
    ReaderhomeComponent,
    GetbooksComponent,
    AuthorloginComponent,
    AuthorhomeComponent,
    CreatebookComponent,
    AuthorsignupComponent,
 
    UpdatebookComponent,
 




  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
