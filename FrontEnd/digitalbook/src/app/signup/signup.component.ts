import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reader } from 'src/model/reader';
import { ReaderService } from 'src/service/reader.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  usernameinvalid:any;
  flag:any;
   r={
    email: 'abc',
    username : 'abc1',
    password :'abc'
   }
  email : string = '';
  username : string = '';
  password : string = '';

  reader : Reader = new Reader();

  constructor( public readerService : ReaderService, private route : Router) { }

  ngOnInit(): void {
    this.username = '';
    this.password = '';
    this.email = '';
  }

 

  signup() {

    this.reader.username = this.username;
    this.reader.password = this.password;
    this.reader.email = this.email;
    this.reader.role = 'user';

    //this.readerService.registerReader(this.reader).subscribe(res => {
      this.readerService.registerReader(this.r).subscribe(res => {
        
    }, err => {
      console.log("RE"+JSON.stringify(err)+err.error.includes("Username"));
     if(err.error.includes("Username")){
       console.log("x");
       this.flag=false;
       this.usernameinvalid=err.error;

      
       
     }
     
      console.log(err.error)
      //alert("Registration failed.");
      //this.ngOnInit();
    })

  }

}
