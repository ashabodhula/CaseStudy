import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reader } from 'src/model/reader';
import { ReaderService } from 'src/service/reader.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  usernameinvalid:any;
  r={
    email: '',
    username : '',
    password :'',
    role:''
   }
   email : string = '';
  username : string = '';
  password : string = '';

  reader : Reader = new Reader();
  constructor(public readerService : ReaderService, private route : Router) { }

  ngOnInit(): void {
   
    this.password = '';
    this.email = '';
  }

 
  login(){
    //this.reader.username = this.username;
    this.reader.password = this.password;
    this.reader.email = this.email;
    this.reader.role = 'user';

    //this.readerService.registerReader(this.reader).subscribe(res => {
      this.readerService.loginUser(this.r).subscribe(res => {
        
    }, err => {
      console.log("RE"+JSON.stringify(err));
     if(err.error.includes("username")){
       this.usernameinvalid=err.console.error();

      
       
     }
     
      console.log(err.error)
      //alert("Registration failed.");
      //this.ngOnInit();
    })

  }
  }

