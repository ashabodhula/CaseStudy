import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reader } from 'src/model/reader';


@Injectable({
  providedIn: 'root'
})
export class ReaderService {
  loginUrl : string = '';
  signUpUrl : string = '';

  constructor(public http :HttpClient) {
    
    this.loginUrl = "http://localhost:8085/digitalbooks/reader/login";
    this.signUpUrl = "http://localhost:8085/digitalbooks/reader/signup";
   }


   registerReader(reader : any)  {
    return this.http.post(this.signUpUrl,reader);
  }

  loginUser(reader : any)  {
    return this.http.post(this.loginUrl,reader);
  }
}
